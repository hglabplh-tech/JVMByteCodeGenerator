(ns hgp.genbytec.generator.generators.javassist-gen.insn-simplified
  (:require [hgp.genbytec.generator.generators.javassist-gen.mini-env :as env])
  (:import (jasmin.utils.jas Insn RuntimeConstants CodeAttr LdcOperand Label)
           (jasmin.utils.jas CPOperand ByteOperand OffsetOperand UnsignedByteOperand UnsignedByteWideOperand
                             RelativeOffsetOperand)))
;; CP code group
(defn putfield-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_putfield, arg)]
    (.addInsn code-attr insn)
    code-attr))

(defn getfield-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_getfield, arg)]
    (.setOperand insn (CPOperand. arg))
    (.addInsn code-attr insn)
    code-attr))

(defn putstatic-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_putstatic, arg)]
    (.addInsn code-attr insn)
    code-attr))

(defn getstttatic-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_getstatic, arg)]
    (.setOperand insn (CPOperand. arg))
    (.addInsn code-attr insn)
    code-attr))

(defn a-new-array-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_anewarray, arg)]
    (.setOperand insn (CPOperand. arg))
    (.addInsn code-attr insn)
    code-attr))

(defn invoke-dyn-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_invokedynamic, arg)]
    (.setOperand insn (CPOperand. arg))
    (.addInsn code-attr insn)
    code-attr))

(defn invoke-non-virt-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_invokenonvirtual, arg)]
    (.setOperand insn (CPOperand. arg))
    (.addInsn code-attr insn)
    code-attr))

(defn invoke-static-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_invokestatic, arg)]
    (.setOperand insn (CPOperand. arg))
    (.addInsn code-attr insn)
    code-attr))

(defn invoke-virtual-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_invokevirtual, arg)]
    (.setOperand insn (CPOperand. arg))
    (.addInsn code-attr insn)
    code-attr))

(defn new-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_new, arg)]
    (.setOperand insn (CPOperand. arg))
    (.addInsn code-attr insn)
    code-attr))

(defn checkcast-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_checkcast, arg)]
    (.setOperand insn (CPOperand. arg))
    (.addInsn code-attr insn)
    code-attr))

;; ldc

(defn ldc2-w-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_ldc2_w, arg)]
    (.setOperand insn (LdcOperand. insn arg))
    (.addInsn code-attr insn)
    code-attr))

(defn ldc-w-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_ldc_w, arg)]
    (.setOperand insn (LdcOperand. insn arg))
    (.addInsn code-attr insn)
    code-attr))

(defn ldc-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_ldc, arg)]
    (.setOperand insn (LdcOperand. insn arg false))
    (.addInsn code-attr insn)
    code-attr))

;; ops which have one numeric argument
(defn bipush-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_bipush value wide?)]
    (.setOperand insn (ByteOperand. value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn sipush-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_sipush value wide?)]
    (.setOperand insn (OffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn sipush-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_sipush value wide?)]
    (.setOperand insn (OffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn goto-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_goto value wide?)]
    (.setOperand insn (OffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn if-acmpeq-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_if_acmpeq value wide?)]
    (.setOperand insn (OffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn if-acmpne-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_if_acmpne value wide?)]
    (.setOperand insn (OffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn if-icmpeq-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_if_icmpeq value wide?)]
    (.setOperand insn (OffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn if-icmpge-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_if_icmpge value wide?)]
    (.setOperand insn (OffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn if-icmpgt-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_if_icmpgt value wide?)]
    (.setOperand insn (OffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn if-icmple-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_if_icmple value wide?)]
    (.setOperand insn (OffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn if-icmplt-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_if_icmplt value wide?)]
    (.setOperand insn (OffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn if-icmpne-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_if_icmpne value wide?)]
    (.setOperand insn (OffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn ifeq-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_ifeq value wide?)]
    (.setOperand insn (OffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn ifge-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_ifge value wide?)]
    (.setOperand insn (OffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn ifgt-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_ifgt value wide?)]
    (.setOperand insn (OffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn ifle-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_ifle value wide?)]
    (.setOperand insn (OffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn iflt-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_iflt value wide?)]
    (.setOperand insn (OffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn ifne-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_ifne value wide?)]
    (.setOperand insn (OffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn ifnonnull-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_ifnonnull value wide?)]
    (.setOperand insn (OffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn ifnull-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_ifnull value wide?)]
    (.setOperand insn (OffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn jsr-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_jsr value wide?)]
    (.setOperand insn (OffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn goto-w-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_goto_w value true)]
    (.setOperand insn (OffsetOperand. insn value true))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn jsr-w-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_jsr_w value true)]
    (.setOperand insn (OffsetOperand. insn value true))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn newarray-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_newarray value)]
    (.setOperand insn (UnsignedByteOperand. value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn newarray-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_newarray value)]
    (.setOperand insn (UnsignedByteOperand. value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn ret-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_ret value)]
    (.setOperand insn (UnsignedByteWideOperand. value true))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn iload-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_iload value)]
    (.setOperand insn (UnsignedByteWideOperand. value true))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lload-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_lload value)]
    (.setOperand insn (UnsignedByteWideOperand. value true))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn fload-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_fload value)]
    (.setOperand insn (UnsignedByteWideOperand. value true))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn dload-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_dload value)]
    (.setOperand insn (UnsignedByteWideOperand. value true))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn aload-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_aload value)]
    (.setOperand insn (UnsignedByteWideOperand. value true))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn istore-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_istore value)]
    (.setOperand insn (UnsignedByteWideOperand. value true))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lstore-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_lstore value)]
    (.setOperand insn (UnsignedByteWideOperand. value true))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn fstore-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_fstore value)]
    (.setOperand insn (UnsignedByteWideOperand. value true))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn dstore-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_dstore value)]
    (.setOperand insn (UnsignedByteWideOperand. value true))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn estore-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_astore value)]
    (.setOperand insn (UnsignedByteWideOperand. value true))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-goto-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_astore value)]
    (.setOperand insn (RelativeOffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-if-acmpeq-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_if_acmpeq value)]
    (.setOperand insn (RelativeOffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-if-acmpne-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_if_acmpne value)]
    (.setOperand insn (RelativeOffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-if-icmpeq-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_if_icmpeq value)]
    (.setOperand insn (RelativeOffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-if-icmpne-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_if_icmpne value)]
    (.setOperand insn (RelativeOffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-if-icmpge-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_if_icmpge value)]
    (.setOperand insn (RelativeOffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-if-icmpgt-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_if_icmpgt value)]
    (.setOperand insn (RelativeOffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-if-icmple-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_if_icmple value)]
    (.setOperand insn (RelativeOffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-if-icmplt-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_if_icmplt value)]
    (.setOperand insn (RelativeOffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-ifeq-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_ifeq value)]
    (.setOperand insn (RelativeOffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-ifge-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_ifge value)]
    (.setOperand insn (RelativeOffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-ifgt-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_ifgt value)]
    (.setOperand insn (RelativeOffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-ifle-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_ifgt value)]
    (.setOperand insn (RelativeOffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-iflt-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_iflt value)]
    (.setOperand insn (RelativeOffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-ifne-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_ifne value)]
    (.setOperand insn (RelativeOffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-ifnonnull-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_ifnonnull value)]
    (.setOperand insn (RelativeOffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-ifnull-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_ifnull value)]
    (.setOperand insn (RelativeOffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-jsr-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_jsr value)]
    (.setOperand insn (RelativeOffsetOperand. insn value))
    (.addInsn code-attr insn)
    code-attr
    ))

;; addressing by label

(defn insert-label [tag]
  (let [label (Label. tag)
        key (gensym tag)]
    (env/add-label key label)
    [key label]))

(defn get-the-label [insert-result]
  (let [[kay
         label] insert-label])
  (env/get-label key)
  )

(defn lab-jsr [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_jsr target lineNum)]
    (.setOperand insn (LabelOperand. insn target  lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-goto [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_goto target lineNum)]
     (.setOperand insn (LabelOperand. insn target  lineNum))
     (.addInsn code-attr insn)
     code-attr
     ))

(defn lab-if-acmpne [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_if_acmpne target lineNum)]
    (.setOperand insn (LabelOperand. insn target  lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-if-acmpeq [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_if_acmpeq target lineNum)]
    (.setOperand insn (LabelOperand. insn target  lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-if-icmpge [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_if_icmpge target lineNum)]
    (.setOperand insn (LabelOperand. insn target  lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-if-icmple [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_if_icmpge target lineNum)]
    (.setOperand insn (LabelOperand. insn target  lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-if-icmpgt [code-attr key lineNum]
(let [target (env/get-label key)
      insn (Insn. RuntimeConstants/opc_if_icmpgt target lineNum)]
  (.setOperand insn (LabelOperand. insn target  lineNum))
  (.addInsn code-attr insn)
  code-attr
  ))

(defn lab-if-icmplt [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_if_icmplt target lineNum)]
    (.setOperand insn (LabelOperand. insn target  lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-if-icmpne [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_if_icmpne target lineNum)]
    (.setOperand insn (LabelOperand. insn target  lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-if-icmpeq [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_if_icmpeq target lineNum)]
    (.setOperand insn (LabelOperand. insn target  lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-ifge [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_ifge target lineNum)]
    (.setOperand insn (LabelOperand. insn target  lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-ifgt [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_ifgt target lineNum)]
    (.setOperand insn (LabelOperand. insn target  lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-ifne [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_ifne target lineNum)]
    (.setOperand insn (LabelOperand. insn target  lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-ifle [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_ifle target lineNum)]
    (.setOperand insn (LabelOperand. insn target  lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-iflt [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_iflt target lineNum)]
    (.setOperand insn (LabelOperand. insn target  lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-ifeq [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_ifeq target lineNum)]
    (.setOperand insn (LabelOperand. insn target  lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-ifnull [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_ifnull target lineNum)]
    (.setOperand insn (LabelOperand. insn target  lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-ifnonnull [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_ifnonnull target lineNum)]
    (.setOperand insn (LabelOperand. insn target  lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-goto-w [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_goto_w target lineNum)]
    (.setOperand insn (LabelOperand. insn target true lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-jsr-w [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_jsr_w target lineNum)]
    (.setOperand insn (LabelOperand. insn target true lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))