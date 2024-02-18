(ns hgp.genbytec.generator.generators.javassist-gen.vse370-svc-handling
  (:require [hgp.genbytec.generator.generators.javassist-gen.mini-env :as env]
            [hgp.genbytec.generator.generators.javassist-gen.util.methods-util :as util]
            [hgp.genbytec.generator.generators.javassist-gen.general-defs :as defs]
            [hgp.genbytec.generator.generators.javassist-gen.op-meth-body-prim-logic :as prim-ops]
            [hgp.genbytec.generator.generators.javassist-gen.machine-part.psw-utils :as psw-u])
  (:import (javassist.bytecode Opcode Bytecode ConstPool)
           (javassist ClassPool)
           (javassist.compiler Javac)
           (ass370.utils VM370Regs))
  )
;;SVC 83 (X'53' - ALLOCATE)

(defn SVC-fun [svc-no]
  (let [base-reg-ara (VM370Regs/getAllBaseRegs)]
    ))