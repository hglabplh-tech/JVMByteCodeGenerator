(ns hgp.genbytec.generator.generators.javassist-gen.method-gen
  (:require [hgp.genbytec.generator.generators.javassist-gen.general-defs :as defs] )
  (:import (javassist ClassPool CtMethod CtNewMethod CtClass Modifier)))
(def test-arr (make-array CtClass 3))
(println test-arr)


(def clazz-types {:int CtClass/intType})

(defn make-modifiers [& modifiers]
  (loop [mod modifiers result 0]
    (if modifiers
      (recur (next modifiers) (bit-or result mod))
      result)))



(defn create-static-meth [decl-clazz name parm-type-arr ret-type modifiers body]
  (let [stat-method (CtMethod. ret-type name parm-type-arr decl-clazz)
        meth-mods (make-modifiers (conj modifiers (get defs/modifier-constants :static)))]
    (.setModifiers stat-method meth-mods)
    (.setBody stat-method body)
    ))

(defn create-public-meth [])

(defn create-private-meth [])

(defn create-protected-meth [])



