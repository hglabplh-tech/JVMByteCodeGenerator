(ns hgp.genbytec.generator.generators.javassist-gen.op-meth-body-prim-logic
  (:require [hgp.genbytec.generator.generators.javassist-gen.mini-env :as env]
            [hgp.genbytec.generator.generators.javassist-gen.general-defs :as defs])
  (:import (javassist.bytecode Opcode Bytecode ConstPool)
           (javassist ClassPool CtClass))
  )

(defn new-byte-node [my-class]
  (let [[const-pool class-pool] (env/get-pools)
        byte-code-inst (Bytecode. const-pool)]
    [const-pool byte-code-inst]
    ))


(defn op-if-icmpeq [byte-code-inst value comp-value key]
  ;; first build the stack
  (.addIload byte-code-inst value)
  (.addIload byte-code-inst comp-value)
  (.addOpcode byte-code-inst (Opcode/IF_ICMPGE))
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

(defn lookup-switch [byte-code-inst block-params]
  (let [addr-before-blocks (.currentPc byte-code-inst)
        count-cases (count block-params)
        return-address (+ (lookup-switch-statement-size byte-code-inst count-cases)
                          (.currentPc byte-code-inst) 1)
        [block-size size-of-blocks]
        (buildup-blocks-for-switch byte-code-inst block-params return-address)
        ]
    (defs/type-constants :booleanType)
    (let [switch-op-code (Opcode/LOOKUPSWITCH)
          ]
      (.addOpcode byte-code-inst switch-op-code)
      (.add byte-code-inst (+ addr-before-blocks 1))
      (.add byte-code-inst count-cases)
      (loop [pair block-params index 1
             address (+ addr-before-blocks
                        (* index block-size) 1)
             ]
        (let [[int-case dummy] pair]
          (.add byte-code-inst int-case)
          (.add byte-code-inst address)
          )
        (recur (rest block-params) (+ index 1) (+ addr-before-blocks
                                                  (* index block-size) 1)))
      )))

