(ns hgp.genbytec.generator.generators.javassist-gen.control-flow
  (:require [hgp.genbytec.generator.generators.javassist-gen.method-gen-add :as  gmeth]
            [hgp.genbytec.generator.generators.javassist-gen.insn-simplified :as insn]
            [hgp.genbytec.generator.generators.javassist-gen.mini-env :as env]
            [hgp.genbytec.generator.generators.javassist-gen.general-defs :as defs]
            )
  (:import (javassist ClassPool CtMethod CtNewMethod CtClass Modifier)))
  (defn gen-method [name ]
        (let [method (gmeth/create-public-method name [CtClass/intType] CtClass/voidType "")
              code-attr (gmeth/get-writer-to-meth name)]
          code-attr
          ))

(def code (gen-method "compileCode"))

(defn while-loop-header []
  (let [start-loop (insn/insert-label "while-lab" )
        [key lab] start-loop
        test (insn/lab-goto code key 5)]                    ;;here comes the compare or any other logical expression
    ))

