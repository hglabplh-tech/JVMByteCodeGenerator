(ns hgp.genbytec.generator.generators.javassist-gen.method-gen-add
  (:require [hgp.genbytec.generator.generators.javassist-gen.mini-env :as env]
            [hgp.genbytec.generator.generators.javassist-gen.general-defs :as defs]
            [hgp.genbytec.generator.generators.javassist-gen.util.methods-util :as mu]
            [hgp.genbytec.generator.generators.javassist-gen.acc-mod-defs :as acc])

  ;; // import jasmin assmbler part for method generation
  (:import (java.lang Class)
           (javassist ClassPool CtMethod CtNewMethod CtClass Modifier)
           (jasmin.utils.jas Method RuntimeConstants CodeAttr Insn)
           (jasmin.utils.jas SignatureAttr ClassEnv ClassCP AsciiCP))
  )

;; signature calculation
(def signatureTypeMap  {
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
(defn array [type] (str "[" type) )
(defn array-of-array [type] (str "[[" type) )
;;L fully-qualified-class ;    fully-qualified-class
;;[type type []
;;Ljava/lang/Object;

(defn build-signature  [params rettype]
  (let [ret-type-id (get signatureTypeMap rettype)
        ret-type-id (if (nil? ret-type-id)
                      (str "L" rettype ";")
                      ret-type-id
                      )]
  (loop [rest-of  params
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
          (recur (rest rest-of)  (str result value) ))
       ))))


;;(println (build-signature [CtClass/intType CtClass/byteType "javassist/CtMethod" ] CtClass/longType))




;;(def clazz-types {:int CtClass/intType})





(defn create-static-meth-javassist [decl-clazz name parm-type-arr ret-type modifiers]
  (let [stat-method (CtMethod. ret-type name parm-type-arr decl-clazz)
        meth-mods (mu/make-modifiers (conj modifiers (get defs/modifier-constants :static)))]
    (.setModifiers stat-method meth-mods)
    ;;(.setBody stat-method body)
    ))

(defn new-class-env [class-name access super-class & interfaces]
  (let [class-cp (ClassCP. class-name)
        class-env (ClassEnv.)]
    (.setClassAccess class-env access)
    (.setClass class-env class-cp)
    (if super-class
    (.setSuperClass class-env super-class))
    (if interfaces
        (.addInterface class-env interfaces))
    ))
(defn add-method-to-class [class-env method]
  (let [[method-decl code-attr] method]
  (.addMethod class-env method-decl)
  )
  )
(defn create-general-method [modifier name parmDesc
                             returnDesc description]
  (let [asciiName (AsciiCP. name)
        asciiDescription (AsciiCP. description)
        new-method (Method. modifier
                            asciiName asciiDescription)
        signatureAttr
        (SignatureAttr. (build-signature parmDesc returnDesc))
        code-attr (CodeAttr.)
        ]
    (.setCode new-method code-attr nil)
    (.setSignature new-method signatureAttr)
    (env/add-method name  [new-method code-attr])
    [new-method code-attr]
    ))

(defn get-writer-to-meth [name]
  (let [[meth code-attr] (env/get-method name)]
    code-attr))


(defn create-public-method [name parmDesc
                            returnDesc description]
  (create-general-method acc/public-method name parmDesc returnDesc description))





