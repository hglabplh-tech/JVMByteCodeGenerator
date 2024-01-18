(ns hgp.genbytec.generator.generators.javassist-gen.general-defs
  (:import (javassist ClassPool CtMethod CtNewMethod CtClass Modifier)))
(def modifier-constants
  {:none         0
   :abstract     (Modifier/ABSTRACT)
   :annotation   (Modifier/ANNOTATION)
   :enum         (Modifier/ENUM)
   :final        (Modifier/FINAL)
   :interface    (Modifier/INTERFACE)
   :native       (Modifier/NATIVE)
   :private      (Modifier/PRIVATE)
   :protected    (Modifier/PROTECTED)
   :public       (Modifier/PUBLIC)
   :static       (Modifier/STATIC)
   :strict       (Modifier/STRICT)
   :synchronized (Modifier/SYNCHRONIZED)
   :transient    (Modifier/TRANSIENT)
   :varargs      (Modifier/VARARGS)
   :volatile     (Modifier/VOLATILE)}
  )

(defn type-constants
  {:booleanType (CtClass/booleanType)
   :byteType    (CtClass/byteType)
   :charType    (CtClass/charType)
   :debugDump   (CtClass/debugDump)
   :doubleType  (CtClass/doubleType)
   :floatType   (CtClass/floatType)
   :intType     (CtClass/intType)
   :longType    (CtClass/longType)
   :shortType   (CtClass/shortType)
   :version     (CtClass/version)
   :voidType    (CtClass/voidType)})
