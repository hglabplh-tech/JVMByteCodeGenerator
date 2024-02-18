(ns hgp.genbytec.generator.generators.javassist-gen.op-meth-body-prim-logic
  (:require [clojure.java.io :as cio]
            [hgp.genbytec.generator.generators.javassist-gen.mini-env :as env]
            [hgp.genbytec.generator.generators.javassist-gen.general-defs :as defs]
            [hgp.genbytec.generator.generators.javassist-gen.machine-defs.opcodes :as opc])
  (:import
    (java.io DataInputStream File)
    (javassist.bytecode Opcode Bytecode ConstPool)
    (javassist ClassPool CtClass))
  )

(defn new-byte-node [my-class]
  (let [[const-pool class-pool] (env/get-pools)
        byte-code-inst (Bytecode. const-pool)]
    [const-pool byte-code-inst]
    ))

(defn file-to-byte-array [file]
  (let [result (byte-array (.length file))]
    (with-open [in (DataInputStream. (cio/input-stream file))]
      (.readFully in result))
    result))

(defn write-bytes-to-code [byte-code-inst the-bytes]
  (for [x the-bytes
        offset 0]
    (do (.write byte-code-inst x) (+ offset 1))))


(defn op-if-icmpeq [byte-code-inst comp-value key]
  ;; first build the stack
  (.addOpcode byte-code-inst Opcode/ILOAD_1)
  (.addIconst byte-code-inst comp-value)
  (.addOpcode byte-code-inst Opcode/IF_ICMPEQ)
  (.add32bit byte-code-inst (env/get-address key)))

(defn op-ifnull [byte-code-inst comp-value key]
  (.addIconst byte-code-inst comp-value)
  (.addOpcode byte-code-inst Opcode/IFNULL)
  (.add32bit byte-code-inst (env/get-address key)))

(defn pseudo-op-iuntil [byte-code-inst value comp-value body-fun]
  (let [label (env/add-current-address byte-code-inst)]
    (body-fun byte-code-inst)
    (let [current-cont (.currentPc byte-code-inst)
          offset-addr (- current-cont (+ (env/get-address label) 1))
          loop-lab (gensym "for-loop-back")
          env-entry (env/add-address loop-lab offset-addr)]
      (op-if-icmpeq byte-code-inst value comp-value loop-lab)))) ;; look for conditions

(defn pseudo-op-iwhile [byte-code-inst value comp-value body-fun end-loop-key]
  (let [loop-head-key (env/add-current-address byte-code-inst)]
    (op-if-icmpeq byte-code-inst value comp-value end-loop-key)
    (body-fun byte-code-inst)

    (let [current-cont (.currentPc byte-code-inst)
          offset-addr (- current-cont (+ (env/get-address loop-head-key) 1))
          loop-key (gensym "for-loop-back")
          env-entry (env/add-address loop-key offset-addr)]
      (op-if-icmpeq byte-code-inst value comp-value loop-key))))



(defn op-goto [key byte-code-inst]
  ^{:doc "defines a goto to a specified address out of the address table "}
  ;; here is the goto target
  ;; int pc = bytecode.currentPc();
  ;; here is the goto
  ;;bytecode.addOpcode(Opcode.GOTO);
  ;;pc2 = bytecode.currentPc();
  ;;bytecode.addIndex(0);

  (let []
    (.addOpcode byte-code-inst (Opcode/GOTO))
    (let [pc (env/get-address key)]
      (.addIndex byte-code-inst (- (.currentPc byte-code-inst) (+ pc 1)))
      byte-code-inst)))

(defn lookup-switch-statement-size [byte-code-inst condition-count]
  (let [pc (.currentPc byte-code-inst)
        sz 8
        sz (+ (if (not= (mod (+ pc 1) 4) 0)
                (- 4 (mod (+ pc 1) 4))
                8) sz)]
    (+ sz (* 8 condition-count))))

(defn block-invocation [byte-code-inst inner-method-name return-address]
  (let [clazz-for-name (env/retrieve-main-class)
        key (env/add-address-simple return-address)]
    (.addInvokestatic clazz-for-name inner-method-name
                      (defs/type-constants :voidType)
                      nil)
    (op-goto key byte-code-inst)
    return-address))

(defn buildup-blocks-for-switch [byte-code-inst block-params return-address]
  (loop [[dummy param] block-params blocks-size 0
         block-size (block-invocation byte-code-inst param return-address)]
    (if (not= 0 (count block-params))
      (recur (rest block-params)
             (+ blocks-size block-size)
             (block-invocation byte-code-inst param return-address))
      [block-size blocks-size])))

(defn  sort-switch-blocks [switch-map]
  (sorted-map-by < switch-map))

(defn calc-addresses [base-pointer switch-map]
  (let [values (vals switch-map)
        keys (keys switch-map)
        map-fun (fn [base-address block] (let [offset (alength block)]
                              (+ base-address offset)))
        addresses (sorted-map-by <)
        ]
    (loop [base-address base-pointer
           val values
           key keys
           addresses (sorted-map-by <)
           ]
             (if (empty? values)
               addresses
             (recur (map-fun base-address val) (rest values) (rest keys)
                    (conj addresses {key (map-fun base-address val)}))))
   ))
(defn compile-to-switch [byte-code-inst switch-map]
  (let [sorted-switch-map (sort-switch-blocks switch-map)]
    (let [switch-op-code (Opcode/LOOKUPSWITCH)
          count-cases (count sorted-switch-map)
          blocks-address (+ (lookup-switch-statement-size byte-code-inst count-cases)
                            (.currentPc byte-code-inst) 1)
          address-map (calc-addresses blocks-address sorted-switch-map)
          ]
      (.addOpcode byte-code-inst switch-op-code)
      (.add byte-code-inst blocks-address)
      (.add byte-code-inst count-cases)
      (loop [addr-element address-map
             ]
        (let [[int-case address]  addr-element]
          (.add byte-code-inst int-case)
          (.add byte-code-inst address)
          )
        (recur
          (rest address-map))
      )
    )
    (let [act-address (.currentPc byte-code-inst)
          the-values (vals sorted-switch-map)
          blocks-length (apply +  the-values)
          jump-final-addr (+ act-address blocks-length 1)
          jump-final-lab (gensym "jump-final-lab")
          dummy (env/add-address jump-final-lab
                           jump-final-addr)]
    (for [block (vals sorted-switch-map)]
     (do (op-goto jump-final-lab byte-code-inst)
      (write-bytes-to-code byte-code-inst block)))
    )))


(defn invoke-meth-static-byte-array-param [byte-code-inst class-name
                                           byte-array-param
                                           method-name ret-type-key]
  (let [[const-pool class-pool] (env/get-pools)]
    (.addInvokestatic byte-code-inst
                      (.get class-pool
                            class-name)
                      method-name
                      (get defs/type-constants ret-type-key)
                      (type byte-array-param)
                      )))




(defn if-statement-and-else? [byte-code-inst the-bytes if-expression the-else-bytes]
  (let [address-before (.currentPc byte-code-inst)
        cond-len (opc/get-opcode-len-of :if_icmpne)
        goto-len (opc/get-opcode-len-of :goto)
        lgoto-lab (gensym "lgoto-lab")
        goto-address
        (env/add-address lgoto-lab
                         (+ cond-len goto-len address-before (alength the-bytes)))]
    (op-ifnull byte-code-inst (eval if-expression) lgoto-lab)
    (write-bytes-to-code byte-code-inst the-bytes)
    (let [alt-address (.currentPc byte-code-inst)
          lafter-else-lab (gensym "lafter-else-lab")
          goto-address
          (env/add-address lafter-else-lab
                           (+ goto-len alt-address (alength the-else-bytes)))]
      (op-goto lafter-else-lab byte-code-inst)
      (write-bytes-to-code byte-code-inst the-else-bytes)
    )))
(defn base-loop-with-body [byte-code-inst the-bytes loop-expression & counter]
  (let [address-before (.currentPc byte-code-inst)
        goto-len (opc/get-opcode-len-of :goto)
        lgoto-lab (gensym "lgoto-lab")
        loop-start-lab (gensym "loop-start-lab")
        goto-address
        (env/add-address lgoto-lab
                         (+ goto-len address-before (alength the-bytes)))
        dummy
        (env/add-address loop-start-lab
                         (+ goto-len address-before))]
    (op-goto lgoto-lab byte-code-inst)
    (write-bytes-to-code byte-code-inst the-bytes)
    (.currentPc byte-code-inst)
    ;; the value for compare must be placed on istore_1
    (op-ifnull byte-code-inst (eval loop-expression) loop-start-lab)
    ))

  (defn open-loop-with-body [byte-code-inst the-bytes loop-expression]
    (let [address-before (.currentPc byte-code-inst)
          goto-len (opc/get-opcode-len-of :goto)
          lgoto-lab (gensym "lgoto-lab")
          loop-start-lab (gensym "loop-start-lab")
          dummy
          (env/add-address loop-start-lab
                           (+ goto-len address-before))]
      (write-bytes-to-code byte-code-inst the-bytes)
      (.currentPc byte-code-inst)
      ;; the value for compare must be placed on istore_1
      (op-ifnull byte-code-inst (eval loop-expression) loop-start-lab)
      ))



