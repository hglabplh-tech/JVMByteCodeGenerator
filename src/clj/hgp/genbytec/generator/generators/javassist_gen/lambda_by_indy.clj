(ns hgp.genbytec.generator.generators.javassist-gen.lambda-by-indy
  (:require [hgp.genbytec.generator.generators.javassist-gen.method-gen-add :as  gmeth]
            [hgp.genbytec.generator.generators.javassist-gen.tokens :as tok]
            [hgp.genbytec.generator.generators.javassist-gen.insn-simplified :as insn]
            [hgp.genbytec.generator.generators.javassist-gen.mini-env :as env])
  (:import (javassist ClassPool CtMethod CtNewMethod CtClass Modifier)
           (jasmin.utils.jas InvokeDynamicCP MethodHandleCP RuntimeConstants BootstrapMethsAttr ClassEnv)))

;;(def class-env (gmeth/new-class-env "LambdaClass" RuntimeConstants/ACC_PUBLIC nil))
(defn parse [fun-def]
  (let [returnType (get fun-def :returnType)
        name (get fun-def :lambda)
        paramList (get fun-def :paramList)]
    [name returnType paramList]
    ))

(defn gen-method [class-env fun-def]
  (let [[name returnType paramList] (parse fun-def)
        method (gmeth/create-public-static-method name paramList returnType)
        code-attr (gmeth/get-writer-to-meth name)
        result (gmeth/add-method-to-class class-env method)]

    [result code-attr]
    ))


(defn new-meth-handle-cp  [ref-kind, ref-index]
  (MethodHandleCP. ref-kind ref-index))

(defn really-invoke-dyn [class-env add-meth-result fun-def]
  (let [[name returnType paramList] (parse fun-def)
        signature (gmeth/build-signature paramList returnType)
        [result code-attr] add-meth-result
        [meth-ref-idx meth-cp] result
        meth-handle-cp (new-meth-handle-cp RuntimeConstants/REF_invokeStatic meth-ref-idx)
        meth-handle-idx (.cpIndex meth-handle-cp class-env)
        bootstrap-meth (BootstrapMethsAttr/createBootstrapMethod meth-handle-idx 0)
        bootstrap-meths-attr (BootstrapMethsAttr. (alength [bootstrap-meth] ) [bootstrap-meth])
        invoke-dynamic-cp  (InvokeDynamicCP. 0 name signature)
        temp (.addCPItem class-env invoke-dynamic-cp)
        inv-dyn-idx (.getCPIndex class-env invoke-dynamic-cp)]
    (insn/invoke-dyn-to-codeattr code-attr invoke-dynamic-cp)
    ))