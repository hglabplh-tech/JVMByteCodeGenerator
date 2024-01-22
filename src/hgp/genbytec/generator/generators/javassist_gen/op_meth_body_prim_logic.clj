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



(defn op-goto [key this-class byte-code-inst]
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

(defn block-invocation [byte-code-inst inner-method-name]
  (let [start-stmt (.currentPc byte-code-inst)
        clazz-for-name (env/retrieve-main-class)
        dummy (.addInvokestatic clazz-for-name inner-method-name
                                (defs/type-constants :voidType)
                                nil)
        end-stmt (.currentPc byte-code-inst)]
    (- end-stmt start-stmt)))

(defn buildup-blocks-for-switch [byte-code-inst block-params]
  (loop [[ dummy param] block-params blocks-size 0
         block-size (block-invocation byte-code-inst param)]
    (if (not= 0 (count block-params))
    (recur (rest block-params)
           (+ blocks-size block-size)
           (block-invocation byte-code-inst param))
    [block-size blocks-size])))

(defn lookup-switch [byte-code-inst block-params]
  (let [addr-before-blocks (.currentPc byte-code-inst)
        [block-size size-of-blocks]
        (buildup-blocks-for-switch byte-code-inst block-params)
        addr-after-blocks (+ 1 (.currentPc byte-code-inst))]
    (defs/type-constants :booleanType)
    (let [switch-op-code (Opcode/LOOKUPSWITCH)
          count-cases (count block-params)]
      (.addOpcode byte-code-inst switch-op-code)
      (.add byte-code-inst (+ addr-before-blocks 1))
      (.add byte-code-inst count-cases)
      (loop [pair block-params  index 1
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

