(ns hgp.genbytec.generator.generators.javassist-gen.insn-simplified
  (:require [hgp.genbytec.generator.generators.javassist-gen.mini-env :as env])
  (:import (java.lang Boolean)
           (jasmin.utils.jas Insn Label LabelOperand RuntimeConstants)
           (jasmin.utils.jas RelativeOffsetOperand MultiarrayInsn)))

;; first of all there is a general functionality for bytecodes without written parameters

(defn opcode-to-codeattr [code-attr op-code]
  (let [insn (Insn. op-code)]
    (.addInsn code-attr insn)
    code-attr
    ))

(defn index-to-code-attr [code-attr index]
  (let [insn (Insn. RuntimeConstants/opc_nop)]
    (.addIndex insn index)
    (.addInsn code-attr insn)
    ))

;; CP code group
(defn putfield-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_putfield arg)]
    (.addInsn code-attr insn)
    code-attr))

(defn getfield-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_getfield arg)]
    (.addInsn code-attr insn)
    code-attr))

(defn putstatic-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_putstatic arg)]
    (.addInsn code-attr insn)
    code-attr))

(defn getstttatic-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_getstatic arg)]

    (.addInsn code-attr insn)
    code-attr))

(defn a-new-array-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_anewarray arg)]

    (.addInsn code-attr insn)
    code-attr))

(defn invoke-dyn-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_invokedynamic, arg)]
    (.addInsn code-attr insn)
    code-attr))

(defn invoke-non-virt-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_invokenonvirtual arg)]

    (.addInsn code-attr insn)
    code-attr))

(defn invoke-static-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_invokestatic arg)]

    (.addInsn code-attr insn)
    code-attr))

(defn invoke-virtual-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_invokevirtual arg)]

    (.addInsn code-attr insn)
    code-attr))

(defn new-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_new arg)]

    (.addInsn code-attr insn)
    code-attr))

(defn checkcast-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_checkcast arg)]

    (.addInsn code-attr insn)
    code-attr))

;; ldc

(defn ldc2-w-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_ldc2_w arg)]

    (.addInsn code-attr insn)
    code-attr))

(defn ldc-w-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_ldc_w arg)]

    (.addInsn code-attr insn)
    code-attr))

(defn ldc-to-codeattr [code-attr arg]
  (let [insn (Insn. RuntimeConstants/opc_ldc arg)]

    (.addInsn code-attr insn)
    code-attr))

;; ops which have one numeric argument
(defn bipush-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_bipush value wide?)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn sipush-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_sipush value wide?)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn sipush-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_sipush value wide?)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn goto-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_goto value wide?)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn if-acmpeq-to-code-attr [code-attr value wide?]
  (let [address (.getPc code-attr insn)
        insn (Insn. RuntimeConstants/opc_if_acmpeq value wide?)]

    (.addInsn code-attr insn)

    ))

(defn if-acmpne-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_if_acmpne value wide?)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn if-icmpeq-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_if_icmpeq value wide?)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn if-icmpge-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_if_icmpge value wide?)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn if-icmpgt-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_if_icmpgt value wide?)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn if-icmple-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_if_icmple value wide?)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn if-icmplt-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_if_icmplt value wide?)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn if-icmpne-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_if_icmpne value wide?)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn ifeq-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_ifeq value wide?)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn ifge-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_ifge value wide?)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn ifgt-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_ifgt value wide?)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn ifle-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_ifle value wide?)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn iflt-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_iflt value wide?)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn ifne-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_ifne value wide?)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn ifnonnull-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_ifnonnull value wide?)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn ifnull-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_ifnull value wide?)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn jsr-to-code-attr [code-attr value wide?]
  (let [insn (Insn. RuntimeConstants/opc_jsr value wide?)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn goto-w-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_goto_w value true)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn jsr-w-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_jsr_w value true)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn newarray-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_newarray value)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn newarray-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_newarray value)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn ret-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_ret value Boolean/TRUE)]
    (.addInsn code-attr insn)
    code-attr
    ))

(defn iload-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_iload value Boolean/TRUE)]
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lload-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_lload value)]
    (.addInsn code-attr insn)
    code-attr
    ))

(defn fload-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_fload value)]
    (.addInsn code-attr insn)
    code-attr
    ))

(defn dload-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_dload value)]
    (.addInsn code-attr insn)
    code-attr
    ))

(defn aload-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_aload value)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn istore-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_istore value)]
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lstore-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_lstore value)]
    (.addInsn code-attr insn)
    code-attr
    ))

(defn fstore-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_fstore value)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn dstore-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_dstore value)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn astore-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_astore value)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-goto-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_astore value)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-if-acmpeq-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_if_acmpeq value)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-if-acmpne-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_if_acmpne value)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-if-icmpeq-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_if_icmpeq value)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-if-icmpne-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_if_icmpne value)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-if-icmpge-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_if_icmpge value)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-if-icmpgt-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_if_icmpgt value)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-if-icmple-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_if_icmple value)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-if-icmplt-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_if_icmplt value)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-ifeq-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_ifeq value)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-ifge-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_ifge value)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-ifgt-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_ifgt value)]

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

    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-ifne-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_ifne value)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-ifnonnull-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_ifnonnull value)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-ifnull-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_ifnull value)]

    (.addInsn code-attr insn)
    code-attr
    ))

(defn rel-jsr-to-code-attr [code-attr value]
  (let [insn (Insn. RuntimeConstants/opc_jsr value)]

    (.addInsn code-attr insn)
    code-attr
    ))

;; addressing by label

(defn insert-label [tag]
  (let [label (Label. tag)
        key tag]
    (env/add-label key label)
    [key label]))

(defn get-the-label [insert-result]
  (let [[key label] insert-label]
    (env/get-label key)
    ))

(defn lab-jsr [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_jsr target lineNum)]
    (.setOperand insn (LabelOperand. insn target lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-goto [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_goto target lineNum)]
    (.setOperand insn (LabelOperand. insn target lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-if-acmpne [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_if_acmpne target lineNum)]
    (.setOperand insn (LabelOperand. insn target lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-if-acmpeq [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_if_acmpeq target lineNum)]
    (.setOperand insn (LabelOperand. insn target lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-if-icmpge [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_if_icmpge target lineNum)]
    (.setOperand insn (LabelOperand. insn target lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-if-icmple [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_if_icmpge target lineNum)]
    (.setOperand insn (LabelOperand. insn target lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-if-icmpgt [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_if_icmpgt target lineNum)]
    (.setOperand insn (LabelOperand. insn target lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-if-icmplt [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_if_icmplt target lineNum)]
    (.setOperand insn (LabelOperand. insn target lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-if-icmpne [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_if_icmpne target lineNum)]
    (.setOperand insn (LabelOperand. insn target lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-if-icmpeq [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_if_icmpeq target lineNum)]
    (.setOperand insn (LabelOperand. insn target lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-ifge [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_ifge target lineNum)]
    (.setOperand insn (LabelOperand. insn target lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-ifgt [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_ifgt target lineNum)]
    (.setOperand insn (LabelOperand. insn target lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-ifne [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_ifne target lineNum)]
    (.setOperand insn (LabelOperand. insn target lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-ifle [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_ifle target lineNum)]
    (.setOperand insn (LabelOperand. insn target lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-iflt [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_iflt target lineNum)]
    (.setOperand insn (LabelOperand. insn target lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-ifeq [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_ifeq target lineNum)]
    (.setOperand insn (LabelOperand. insn target lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-ifnull [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_ifnull target lineNum)]
    (.setOperand insn (LabelOperand. insn target lineNum))
    (.addInsn code-attr insn)
    code-attr
    ))

(defn lab-ifnonnull [code-attr key lineNum]
  (let [target (env/get-label key)
        insn (Insn. RuntimeConstants/opc_ifnonnull target lineNum)]
    (.setOperand insn (LabelOperand. insn target lineNum))
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


;; MultiArray

(defn multiarray-to-codeattr [code-attr cp dimensions]
  (let [insn (MultiarrayInsn. cp dimensions)]
    (.addInsn code-attr insn)
    code-attr))