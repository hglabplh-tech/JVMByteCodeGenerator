(ns hgp.genbytec.generator.generators.javassist-gen.op-for-370-instruction-set
  (:require [hgp.genbytec.generator.generators.javassist-gen.mini-env :as env]
            [hgp.genbytec.generator.generators.javassist-gen.util.methods-util :as util]
            [hgp.genbytec.generator.generators.javassist-gen.general-defs :as defs]
            [hgp.genbytec.generator.generators.javassist-gen.op-meth-body-prim-logic :as prim-ops]
            [hgp.genbytec.generator.generators.javassist-gen.machine-part.psw-utils :as psw-u])
  (:import (javassist.bytecode Opcode Bytecode ConstPool)
           (javassist ClassPool)
           (javassist.compiler Javac)
           (ass370.utils VM370Regs)
           (bytecode.addon AdditionalWrappedOps))
  )

(defn getter-get-javac [byte-code-inst ct-clazz]
  (let [java-c (Javac. byte-code-inst ct-clazz)]
    (fn get-java-c [] java-c)))
(def java-comp (getter-get-javac (util/new-byte-code) (env/retrieve-main-class)))


(defn BALR-fun [byte-code-inst reg-next-op reg-goto-target]
  (let [act-addr (.currentPc byte-code-inst)
        calculated-next (+ act-addr)]
    (swap! reg-next-op calculated-next)
    (prim-ops/op-goto reg-goto-target byte-code-inst)
    ))

(defn BCR-fun [byte-code-inst mask reg-goto-target]
  (let [class-pool (ClassPool/getDefault)
        result (.addInvokestatic byte-code-inst
                                 (.get class-pool
                                       "ass370.utils.VMStatusUtil")
                                 "getPswCC"
                                 (get defs/type-constants :byteType)
                                 nil
                                 )]
    (case result
      psw-u/equal-mask
      (do (if (= psw-u/equal-mask mask)
            (do
              (.addOpcode byte-code-inst Opcode/IFEQ)
              (.add reg-goto-target))))
      psw-u/high-mask
      (do (if (= psw-u/high-mask mask)
            (do
              (.addOpcode byte-code-inst Opcode/IFGT)
              (.add reg-goto-target))))
      psw-u/low-mask
      (do (if (= psw-u/low-mask mask)
            (do
              (.addOpcode byte-code-inst Opcode/IFLT)
              (.add reg-goto-target))))

      )))



(defn CLR-fun [byte-code-inst reg-no-one reg-no-two op-code-comp]
  (AdditionalWrappedOps/loadRegs reg-no-one)
  (AdditionalWrappedOps/loadRegs reg-no-two)
  (.addOpcode byte-code-inst op-code-comp)
  (prim-ops/lookup-switch byte-code-inst [[0 "setPswCCEq"]
                                          [1 "setPswCCGt"]
                                          [-1 "setPswCCLt"]]))


(defn NR-fun [byte-code-inst reg-no-one reg-no-two]

  (AdditionalWrappedOps/loadRegs reg-no-one)
  (AdditionalWrappedOps/loadRegs reg-no-two)
  (.addOpcode byte-code-inst Opcode/IAND)
  (AdditionalWrappedOps/storeRegs reg-no-one)
  )

(defn AR-fun [byte-code-inst reg-no-one reg-no-two]
  (AdditionalWrappedOps/loadRegs reg-no-one)
  (AdditionalWrappedOps/loadRegs reg-no-two)
  (.addOpcode Opcode/IADD)
  (AdditionalWrappedOps/storeRegs reg-no-one);; check if


  )

(defn DR-fun [byte-code-inst reg-no-one reg-no-two]
  (AdditionalWrappedOps/loadRegs reg-no-one)
  (AdditionalWrappedOps/loadRegs reg-no-two)
  (.addOpcode Opcode/IDIV)
  (AdditionalWrappedOps/storeRegs reg-no-one)
                              ;; check if
  ;; index is correct or if we have to find another way

  )

(defn XR-fun [byte-code-inst reg-no-one reg-no-two]
  (AdditionalWrappedOps/loadRegs reg-no-one)
  (AdditionalWrappedOps/loadRegs reg-no-two)
  (.addOpcode Opcode/IXOR)
  (.addIstore byte-code-inst 0))