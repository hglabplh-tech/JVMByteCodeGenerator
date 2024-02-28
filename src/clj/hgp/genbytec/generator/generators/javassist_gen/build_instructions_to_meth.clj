(ns hgp.genbytec.generator.generators.javassist-gen.build-instructions-to-meth
  (:require [hgp.genbytec.generator.generators.javassist-gen.acc-mod-defs :as acc]
            [hgp.genbytec.generator.generators.javassist-gen.insn-simplified :as insn]
            [hgp.genbytec.generator.generators.javassist-gen.general-defs :as defs]
            [hgp.genbytec.generator.generators.javassist-gen.mini-env :as env]
            [hgp.genbytec.generator.generators.javassist-gen.method-gen-add :as meth-gen]
            )

  ;; // import jasmin assmbler part for method generation
  (:import (jasmin.utils.jas AsciiCP ClassCP ClassEnv NameTypeCP)
           (jasmin.utils.jas CodeAttr Method MethodCP SignatureAttr RuntimeConstants)
           (javassist CtClass CtMethod))
  )

(defn load-arguments-to-local [code-attr param-desc start-index]
  (loop [params param-desc
         cmdidx start-index]
    (case (first params)
      CtClass/intType (insn/istore-to-code-attr code-attr cmdidx)
      CtClass/longType (insn/lstore-to-code-attr code-attr cmdidx)
      CtClass/floatType (insn/fstore-to-code-attr code-attr cmdidx)
      CtClass/doubleType (insn/dstore-to-code-attr code-attr cmdidx)
      (insn/astore-to-code-attr code-attr cmdidx)
      )
    (recur (next params) (+ cmdidx 1))
    ))

(defn return-from-meth [code-attr return-type value-idx]
  (case (first return-type)
    CtClass/intType (insn/iload-to-code-attr code-attr value-idx)
    CtClass/longType (insn/lload-to-code-attr code-attr value-idx)
    CtClass/floatType (insn/fload-to-code-attr code-attr value-idx)
    CtClass/doubleType (insn/dload-to-code-attr code-attr value-idx)
    (insn/aload-to-code-attr code-attr value-idx)           ;; here the value does not matter
    )
  (case (first return-type)
    CtClass/intType (insn/opcode-to-codeattr code-attr RuntimeConstants/opc_ireturn)
    CtClass/longType (insn/opcode-to-codeattr code-attr RuntimeConstants/opc_lreturn)
    CtClass/floatType (insn/opcode-to-codeattr code-attr RuntimeConstants/opc_freturn)
    CtClass/doubleType (insn/opcode-to-codeattr code-attr RuntimeConstants/opc_dreturn)
    (insn/opcode-to-codeattr code-attr RuntimeConstants/opc_areturn)
    )
  )

(defn load-this [code-attr]
  (insn/aload-to-code-attr code-attr 0)
  )

(defn build-method-entry [new-method code-attr param-desc acc-modifiers]
  (let [first-param-index (if (and acc-modifiers RuntimeConstants/ACC_STATIC)
                            0 1)]
    (if (= first-param-index 1) (load-this code-attr))
    (load-arguments-to-local code-attr param-desc first-param-index)
    (+ count param-desc first-param-index)                  ;; count of stack entries
    ))

(defn invoke-meth [class-env class-name meth-name signature code-attr param-desc ret-type start-index invoke-type]
  (case invoke-type
    RuntimeConstants/REF_invokeStatic (insn/opcode-to-codeattr code-attr RuntimeConstants/opc_invokestatic)
    RuntimeConstants/REF_invokeSpecial (insn/opcode-to-codeattr code-attr RuntimeConstants/opc_invokespecial)
    RuntimeConstants/REF_invokeVirtual (insn/opcode-to-codeattr code-attr RuntimeConstants/opc_invokevirtual)
    )
  (let [meth-index (.getMethodIdxByName class-env class-name meth-name signature)]
    (insn/index-to-code-attr code-attr meth-index)
    )

  )

(defn call-non-dyn-meth [class name signature code-attr param-desc ret-type start-index invoke-type]
  (let []
    (loop [params param-desc
           cmdidx start-index]
      (case (first params)
        CtClass/intType (insn/iload-to-code-attr code-attr cmdidx)
        CtClass/longType (insn/lload-to-code-attr code-attr cmdidx)
        CtClass/floatType (insn/fload-to-code-attr code-attr cmdidx)
        CtClass/doubleType (insn/dload-to-code-attr code-attr cmdidx)
        (insn/aload-to-code-attr code-attr cmdidx))
      (recur (next params) (+ cmdidx 1)))
    (invoke-meth class name signature code-attr param-desc ret-type start-index invoke-type)
    ))

