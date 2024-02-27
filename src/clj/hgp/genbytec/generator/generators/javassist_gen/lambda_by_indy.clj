(ns hgp.genbytec.generator.generators.javassist-gen.lambda-by-indy
  (:require [hgp.genbytec.generator.generators.javassist-gen.insn-simplified :as insn]
            [hgp.genbytec.generator.generators.javassist-gen.method-gen-add :as gmeth]
            [hgp.genbytec.generator.generators.javassist-gen.mini-env :as env]
            [hgp.genbytec.generator.generators.javassist-gen.parse-thing :as parse])
  (:import (jasmin.utils.jas BootstrapMethsAttr InvokeDynamicCP MethodHandleCP RuntimeConstants)))

;;(def class-env (gmeth/new-class-env "LambdaClass" RuntimeConstants/ACC_PUBLIC nil))


(defn gen-method [class-env fun-def]
  (let [[name return-type param-list] (parse/parse fun-def)
        method (gmeth/create-public-static-method name param-list return-type)
        code-attr (gmeth/get-writer-to-meth name)
        result (gmeth/add-method-to-class class-env method)]

    [result code-attr]
    ))


(defn new-meth-handle-cp  [ref-kind, ref-index]
  (MethodHandleCP. ref-kind ref-index))

(defn really-invoke-dyn [class-env add-meth-result fun-def]
  (let [[name return-type param-list] (parse/parse fun-def)
        signature (gmeth/build-signature param-list return-type)
        [result code-attr] add-meth-result
        [meth-ref-idx meth-cp] result
        meth-handle-cp (new-meth-handle-cp RuntimeConstants/REF_invokeStatic meth-ref-idx)
        meth-handle-idx (.cpIndex meth-handle-cp class-env)
        bootstrap-meth-arr (BootstrapMethsAttr/createBootstrapMethod meth-handle-idx 0)
        array-size (alength bootstrap-meth-arr)
        bootstrap-meths-attr (BootstrapMethsAttr.  array-size bootstrap-meth-arr)
        invoke-dynamic-cp  (InvokeDynamicCP. 0 name signature)
        temp (.addCPItem class-env invoke-dynamic-cp)
        inv-dyn-idx (.calcCPIndex class-env invoke-dynamic-cp)]
    (env/add-address name (insn/invoke-dyn-to-codeattr code-attr invoke-dynamic-cp))
    ))

(def test-expr {:returnType :void
                :lambda     "lambdaFun"
                :paramList  [:any :any :any :any :long]})

(def cenvironment (gmeth/new-class-env "CompileClass" RuntimeConstants/ACC_PUBLIC) )
(def code-res (really-invoke-dyn cenvironment (gen-method
                                                cenvironment
                                                test-expr) test-expr))

(println code-res)