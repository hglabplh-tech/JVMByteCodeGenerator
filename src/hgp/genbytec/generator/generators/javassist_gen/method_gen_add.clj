(ns hgp.genbytec.generator.generators.javassist-gen.method-gen-add
  (:require [hgp.genbytec.generator.generators.javassist-gen.general-defs :as defs]
            [hgp.genbytec.generator.generators.javassist-gen.util.methods-util :as mu])
  (:import (javassist ClassPool CtMethod CtNewMethod CtClass Modifier)))
(def test-arr (make-array CtClass 3))
(println test-arr)


(def clazz-types {:int CtClass/intType})





(defn create-static-meth [decl-clazz name parm-type-arr ret-type modifiers]
  (let [stat-method (CtMethod. ret-type name parm-type-arr decl-clazz)
        meth-mods (mu/make-modifiers (conj modifiers (get defs/modifier-constants :static)))]
    (.setModifiers stat-method meth-mods)
    ;;(.setBody stat-method body)
    ))

(defn create-public-meth [])

(defn create-private-meth [])

(defn create-protected-meth [])


;; add a method
;;CtMethod getSalary = CtNewMethod.make
;;(Modifier.PUBLIC, CtClass.doubleType, "getSalary", null, null,
;;        "return salary;", ctClass)                ;
;;ctClass.addMethod(getSalary);
;;JavaDoc
;;public static CtMethod make
;;(String src,
;;CtClass declaring,
;;String delegateObj,
;;String delegateMethod)
;;throws CannotCompileException

(defn add-method-to-given-class [])


