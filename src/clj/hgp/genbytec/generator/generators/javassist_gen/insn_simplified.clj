(ns hgp.genbytec.generator.generators.javassist-gen.insn-simplified
  (:import (jasmin.utils.jas Insn RuntimeConstants CodeAttr LdcOperand)))


;; CP code group
(defn putfield-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_putfield, arg)]
    (.addInsn code-attr insn)
    insn))

(defn getfield-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_getfield, arg)]
    (.addInsn code-attr insn)
    insn))

(defn a-new-array-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_anewarray, arg)]
    (.addInsn code-attr insn)
    insn))

(defn invoke-dyn-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_invokedynamic, arg)]
    (.addInsn code-attr insn)
    insn))

(defn invoke-non-virt-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_invokenonvirtual, arg)]
    (.addInsn code-attr insn)
    insn))

(defn invoke-static-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_invokestatic, arg)]
    (.addInsn code-attr insn)
    insn))

(defn invoke-virtual-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_invokevirtual, arg)]
    (.addInsn code-attr insn)
    insn))

;; ldc

(defn ldc2-w-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_ldc2_w, arg)]
    (.setOperand insn (LdcOperand. insn arg ) )
    (.addInsn code-attr insn)
    insn))

(defn ldc-w-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_ldc_w, arg)]
    (.setOperand insn (LdcOperand. insn arg ) )
    (.addInsn code-attr insn)
    insn))

(defn ldc-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_ldc, arg)]
    (.setOperand insn (LdcOperand. insn arg false) )
    (.addInsn code-attr insn)
    insn))
