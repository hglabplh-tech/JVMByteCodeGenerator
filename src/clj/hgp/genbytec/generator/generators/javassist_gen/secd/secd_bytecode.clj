(ns hgp.genbytec.generator.generators.javassist-gen.secd.secd-bytecode
  (:require
    [hgp.genbytec.generator.generators.javassist-gen.insn-simplified :as insn]
    [hgp.genbytec.generator.generators.javassist-gen.method-gen-add :as meth]
    [hgp.genbytec.generator.generators.javassist-gen.machine-defs.opcodes :as oc]
    [hgp.genbytec.generator.generators.javassist-gen.secd.secd-core-lang :as cl]
    [hgp.genbytec.generator.generators.javassist-gen.secd.secd-core-lang as cl]
    )
  (:import (java.lang Class IllegalArgumentException Void)
           (java.lang.invoke MethodType)
           (jasmin.utils.jas AsciiCP ClassCP ClassEnv NameTypeCP StringCP LongCP FloatCP IntegerCP DoubleCP AsciiCP)
           (jasmin.utils.jas CodeAttr Method MethodCP SignatureAttr RuntimeConstants)
           (bytecode.runtime TypeUtils ByteCodeEnv ByteCodeDump MachineAndAdminUtil)
           (javassist CtClass CtMethod)))

;; check the places wheree we need addCPItem

(def primitives-mapping {
                         :integer {'+ RuntimeConstants/opc_iadd
                                   '- RuntimeConstants/opc_isub
                                   '/ RuntimeConstants/opc_idiv
                                   '* RuntimeConstants/opc_imul
                                   '% RuntimeConstants/opc_irem
                                   }
                         :long    {'+ RuntimeConstants/opc_ladd
                                   '- RuntimeConstants/opc_lsub
                                   '/ RuntimeConstants/opc_ldiv
                                   '* RuntimeConstants/opc_lmul
                                   '% RuntimeConstants/opc_lrem
                                   }
                         :float   {'+ RuntimeConstants/opc_fadd
                                   '- RuntimeConstants/opc_fsub
                                   '/ RuntimeConstants/opc_fdiv
                                   '* RuntimeConstants/opc_fmul
                                   '% RuntimeConstants/opc_frem
                                   }
                         :double  {'+ RuntimeConstants/opc_dadd
                                   '- RuntimeConstants/opc_dsub
                                   '/ RuntimeConstants/opc_ddiv
                                   '* RuntimeConstants/opc_dmul
                                   '% RuntimeConstants/opc_drem
                                   }
                         })
(def ctor-method-name "<init>")
(def code-env (atom {}))

(defn add-to-code-env [key thing]
  (let [pair {key thing}]
    (swap! code-env conj pair)
    pair))

(defn remove-from-code-env [key]
  (swap! code-env dissoc key)
  key)

(defn lookup-code-env-entry [key]
  (get @code-env key))

(defn get-actual-environment []
  (let [code-attr (lookup-code-env-entry :act-method)
        act-env-getter-class-name "bytecode.runtime.MachineAndAdminUtil"
        act-env-getter-meth-name "actualEnv"
        method-type-en-getter ((MethodType/methodType (Class/forName "java.lang.Void")
                                                      []))
        act-env-getter-meth-sig (.descriptorString method-type-en-getter)
        act-env-getter-meth-cp (MethodCP. act-env-getter-class-name act-env-getter-meth-name act-env-getter-meth-sig)]
    (insn/invoke-static-to-codeattr code-attr act-env-getter-meth-cp)
    ))
(defn new-multi-dim-array [size-env-stack size-data-area clazz-name]
  (let [code-attr (lookup-code-env-entry :act-method)
        the-class-cp (ClassCP. clazz-name)]
    (insn/multiarray-to-codeattr code-attr the-class-cp 2)
    ))

(defn get-from-multi-array [loc-env-index loc-index]
  (let [code-attr (lookup-code-env-entry :act-method)]
    (insn/iload-to-code-attr code-attr loc-env-index)
    (insn/opcode-to-codeattr code-attr RuntimeConstants/opc_aaload)
    (insn/aload-to-code-attr code-attr loc-index)
    ))

(defn make-class-with-main []
  (let [type-test-arr (make-array java.lang.Object 1)
        in-array-type (type type-test-arr)]
    (meth/create-public-static-method "main" [in-array-type] CtClass/voidType) ;;look how to remember code-attr 2nd Parm in return
    ))

(defn make-program-class [class-name]
  (let [class-env (meth/new-class-env class-name RuntimeConstants/ACC_PUBLIC)
        [code-attr dummy] (meth/add-method-to-class class-env (make-class-with-main))
        ]
    (add-to-code-env (gensym (str class-name "$main")) code-attr)
    (add-to-code-env :act-method code-attr)
    (add-to-code-env (gensym class-name) code-attr)
    (add-to-code-env :act-class-env class-env)
    code-attr
    )
  )

(defn add-closure-code-meth [class-name meth-name return-type param-array]
  (let [class-env (lookup-code-env-entry :act-class-env)
        [new-meth code-attr] (meth/create-public-static-method meth-name param-array return-type)
        ]
    (add-to-code-env (gensym (str class-name "$" meth-name)) code-attr)
    (add-to-code-env :act-method code-attr)
    (meth/add-method-to-class class-env new-meth)))

(defn push-const [type constant-val]
  (let [class-env (lookup-code-env-entry :act-class-env)
        code-attr (lookup-code-env-entry :act-method)
        type-as-string (cl/var-type->string type)
        typed-cp (TypeUtils/makeTypedCP class-env type-as-string constant-val)
        result (insn/ldc-to-codeattr code-attr typed-cp)]
    result
    ))


(defn get-value-from-binding []
  (let [
        bind-class-name "bytecode.runtime.ByteCodeEnv.Binding"
        code-attr (lookup-code-env-entry :act-method)
        bind-value-meth-type (MethodType/methodType (Class/forName "java.lang.Object")
                                                    [])
        bind-value-signature (.descriptorString bind-value-meth-type)
        bind-get-value-meth-cp (MethodCP. bind-class-name "value" bind-value-signature)
        ]
    (insn/aload-to-code-attr code-attr 0)
    (insn/invoke-virtual-to-codeattr code-attr bind-get-value-meth-cp)
    (insn/astore-to-code-attr code-attr 3)))

(defn get-optional-check-present []
  (let
    [
     optclass-name "java.util.Optional"
     code-attr (lookup-code-env-entry :act-method)
     opt-clazz (Class/forName optclass-name)
     optmethod-type (MethodType/methodType opt-clazz
                                           [])
     opt-signature (.descriptorString optmethod-type)
     opt-ispresent-meth-cp (MethodCP. optclass-name "isPresent" opt-signature)
     opt-get-meth-cp (MethodCP. optclass-name "get" opt-signature)
     ]
    (insn/aload-to-code-attr code-attr 0)
    (insn/invoke-virtual-to-codeattr code-attr opt-ispresent-meth-cp)
    (insn/istore-to-code-attr code-attr 1)
    (insn/aload-to-code-attr code-attr 0)
    (insn/invoke-virtual-to-codeattr code-attr opt-get-meth-cp)
    (insn/astore-to-code-attr code-attr 2)

    ))
(defn environment-lookup [key]
  (let [class-name "bytecode.runtime.ByteCodeEnv"
        code-attr (lookup-code-env-entry :act-method)
        class-env (lookup-code-env-entry :act-class-env)
        key-cp (StringCP. key)
        method-type (MethodType/methodType (Class/forName "java.lang.String")
                                           [])
        signature (.descriptorString method-type)
        method-name "lookupBinding"
        method-cp (MethodCP. class-name method-name signature)
        illegal-arg-class-name "java.lang.IllegalArgumentException"
        excp-cp (ClassCP. illegal-arg-class-name)
        excp-method-type (MethodType/methodType (Class/forName "java.lang.Void")
                                                [(Class/forName "java.lang.String")])
        ctor-sig (.descriptorString excp-method-type)
        ctor-method-cp (MethodCP. illegal-arg-class-name ctor-method-name ctor-sig)
        excp-message-cp (AsciiCP. "No object with this  key is found in environment")
        jump-addr-if-cmp (+ (oc/get-opcode-len-of :if_icmpne) (oc/get-opcode-len-of :aload)
                            (oc/get-opcode-len-of :invokevirtual) (oc/get-opcode-len-of :goto))
        jump-addr-goto (+ (oc/get-opcode-len-of :goto) (oc/get-opcode-len-of :new)
                          (oc/get-opcode-len-of :ldc) (oc/get-opcode-len-of :invokevirtual)
                          (oc/get-opcode-len-of :athrow))
        ]
    (get-actual-environment)
    (insn/ldc-to-codeattr code-attr key-cp)                 ;; we must get the class / object reference
    (insn/invoke-virtual-to-codeattr code-attr method-cp)
    (insn/astore-to-code-attr code-attr 0)
    (get-optional-check-present)
    (insn/iload-to-code-attr code-attr 1)
    (insn/opcode-to-codeattr code-attr RuntimeConstants/opc_iconst_1)
    (insn/if-icmpne-to-code-attr code-attr jump-addr-if-cmp false)
    (get-value-from-binding)
    (insn/aload-to-code-attr code-attr 3)
    (insn/goto-to-code-attr code-attr jump-addr-goto true)
    (.addCPItem class-env excp-cp)
    (insn/new-to-codeattr code-attr excp-cp)
    ;; call constructor
    (insn/ldc-to-codeattr code-attr excp-message-cp)
    (insn/invoke-virtual-to-codeattr code-attr ctor-method-cp)
    ;; throw it
    (insn/opcode-to-codeattr code-attr RuntimeConstants/opc_athrow) ;;here comes the
    ;; exception
    (insn/opcode-to-codeattr code-attr RuntimeConstants/opc_nop)
    ))

(defn get-op-by-type [type]
  (let [binding]))
(defn execute-op-by-type [op-sym type]
  (let [code-attr (lookup-code-env-entry :act-method)
        typed-ops (get primitives-mapping type)
        typed-code (if typed-ops
                     (get typed-ops op-sym))]
    (if typed-code
      (insn/opcode-to-codeattr code-attr typed-code)
      (insn/opcode-to-codeattr code-attr RuntimeConstants/opc_nop)
      )
    ))

(defn add-binding [reference-name value type-id]
  (let [class-name "bytecode.runtime.ByteCodeEnv"
        void (Void.)
        method-type (MethodType/methodType (.getClass void)
                                           [(Class/forName "java.lang.String")
                                            (Class/forName "java.lang.Object")
                                            (Class/forName "java.lang.String")])
        signature (.descriptorString method-type)
        method-name "addBinding"
        method-cp (MethodCP. class-name method-name signature)
        code-attr (lookup-code-env-entry :act-method)
        ref-cp (StringCP. reference-name)
        value-cp (ClassCP. "java.lang.Object")
        type-id-cp (StringCP. type-id)
        ]
    ;; get the reference of the environment we hav to create a initial instance and
    ;; aftr that a new instance for each new abstract
    (get-actual-environment)
    (insn/ldc-to-codeattr code-attr ref-cp)
    (insn/ldc-to-codeattr code-attr value-cp)
    (insn/ldc-to-codeattr code-attr type-id-cp)
    (insn/invoke-virtual-to-codeattr code-attr method-cp)
    ))



