(ns hgp.genbytec.generator.generators.javassist-gen.method-gen-add
  (:require [hgp.genbytec.generator.generators.javassist-gen.acc-mod-defs :as acc]
            [hgp.genbytec.generator.generators.javassist-gen.general-defs :as defs]
            [hgp.genbytec.generator.generators.javassist-gen.mini-env :as env]
            [hgp.genbytec.generator.generators.javassist-gen.util.methods-util :as mu])

  ;; // import jasmin assmbler part for method generation
  (:import (jasmin.utils.jas AsciiCP ClassCP ClassEnv NameTypeCP)
           (jasmin.utils.jas CodeAttr Method MethodCP SignatureAttr)
           (javassist CtClass CtMethod))
  )

;; signature calculation
(def signatureTypeMap {
                       CtClass/booleanType "Z"
                       CtClass/byteType    "B"
                       CtClass/charType    "C"
                       CtClass/shortType   "S"
                       CtClass/intType     "I"
                       CtClass/longType    "J"
                       CtClass/floatType   "F"
                       CtClass/doubleType  "D"
                       CtClass/voidType    "V"

                       })
(defn array [type] (str "[" type))
(defn array-of-array [type] (str "[[" type))
;;L fully-qualified-class ;    fully-qualified-class
;;[type type []
;;Ljava/lang/Object;

(defn build-signature [params rettype]
  (let [ret-type-id (get signatureTypeMap rettype)
        ret-type-id (if (nil? ret-type-id)
                      (str "L" rettype ";")
                      ret-type-id
                      )]
    (loop [rest-of params
           result "("
           ]
      (if (empty? rest-of)
        (str result ")" ret-type-id)
        (let [parm (first rest-of)
              value (get signatureTypeMap parm)
              value (if (nil? value)
                      (str "L" parm ";")
                      value
                      )
              ]
          (recur (rest rest-of) (str result value)))
        ))))




(defn new-class-env [class-name access  & addons]
  (let [class-cp (ClassCP. class-name)
        class-env (ClassEnv.)]
    (.setClassAccess class-env access)
    (.setClassCP class-env class-cp)
    class-env))


(defn add-method-to-class [class-env method]
  (let [[method-decl code-attr] method
        class-CP (.getClassCP class-env)
        meth-CP (MethodCP. (.strName class-CP)
                           (.name method-decl)
                           (.signatureVal
                             (.getSignatureAttr method-decl)))]
    (.addMethod class-env method-decl)
    (.addCPItem class-env meth-CP)
    [(.calcCPIndex class-env  meth-CP) meth-CP]
    ))
(defn create-general-method [modifier name parmDesc
                             returnDesc]
  (let [asciiName (AsciiCP. name)
        sig-def (build-signature parmDesc returnDesc)
        signatureAttr
        (SignatureAttr. sig-def)
        asciiDescription (AsciiCP. sig-def)
        new-method (Method. modifier
                            asciiName asciiDescription)
        code-attr (CodeAttr.)
        ]
    (.setCode new-method code-attr nil)
    (.setSignature new-method signatureAttr)
    (env/add-meth name [new-method code-attr])

    [new-method code-attr]
    ))

(defn get-writer-to-meth [name]
  (let [[meth code-attr] (env/get-meth name)]
    code-attr))


(defn create-public-method [name parmDesc
                            returnDesc]
  (create-general-method acc/public-method name parmDesc returnDesc))

(defn create-public-static-method [name parmDesc
                                   returnDesc ]
  (create-general-method acc/public-static-method name parmDesc returnDesc))





