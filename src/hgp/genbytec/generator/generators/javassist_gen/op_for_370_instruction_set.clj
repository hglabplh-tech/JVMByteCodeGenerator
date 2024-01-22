(ns hgp.genbytec.generator.generators.javassist-gen.op-for-370-instruction-set
  (:require [hgp.genbytec.generator.generators.javassist-gen.mini-env :as env]
            [hgp.genbytec.generator.generators.javassist-gen.util.methods-util :as util]
            [hgp.genbytec.generator.generators.javassist-gen.general-defs :as defs]
            [hgp.genbytec.generator.generators.javassist-gen.op-meth-body-prim-logic :as prim-ops])
  (:import (javassist.bytecode Opcode Bytecode ConstPool)
           (javassist ClassPool)
           (javassist.compiler Javac)
           (java.lang Class)
           (ass370.utils VMStatusUtil))
  )

(defn getter-get-javac [byte-code-inst ct-clazz]
  (let [java-c (Javac. byte-code-inst ct-clazz)]
    (fn get-java-c [] java-c)))
  (def java-comp (getter-get-javac (util/new-byte-code) (env/retrieve-main-class)))


(defn BALR-fun [byte-code-inst this-clazz reg-next-op reg-goto-target]
  (let [act-addr (.currentPc byte-code-inst)
        calculated-next (+ act-addr)]
    (swap! reg-next-op calculated-next)
    (prim-ops/op-goto reg-goto-target this-clazz byte-code-inst)
    ))
  (defn BCR-fun [byte-code-inst this-clazz mask reg-goto-target]
    (let [class-pool (ClassPool/getDefault)
          result (.addInvokestatic byte-code-inst
                                   (.get class-pool
                                         "ass370.utils.VMStatusUtil")
                                   "getPswCC"
                                   (get defs/type-constants :byteType)
                                   nil
                                   )])
        )
  (defn CLR-fun [byte-code-inst this-clazz reg-value reg-value-comp]
        (let [op-code-comp (Opcode/LCMP)]
          (.addIload byte-code-inst reg-value)
          (.addIload byte-code-inst reg-value-comp)
          (.addOpcode byte-code-inst op-code-comp)
          (prim-ops/lookup-switch byte-code-inst [[0 "setPswCCEq"]
                                                  [1  "setPswCCGt"]
                                                  [-1 "setPswCCLt"]])))

  ;; lcmp