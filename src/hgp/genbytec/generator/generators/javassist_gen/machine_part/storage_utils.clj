(ns hgp.genbytec.generator.generators.javassist-gen.machine-part.storage-utils
  (:require [hgp.genbytec.generator.generators.javassist-gen.general-defs :as gd]
            [hgp.genbytec.generator.generators.javassist-gen.machine-part.register-utils :as ru]
            [hgp.genbytec.generator.generators.javassist-gen.mini-env :as env])
  (:import (java.lang Object Class)
           (javassist ClassPool)
           (javassist.bytecode Bytecode)))

(def short-type (gd/get-type-const :shortType))
(def int-type (gd/get-type-const :intType))

(def long-type (gd/get-type-const :longType))

(def string-type (gd/get-type-const :intType))

(def float-type (gd/get-type-const :floatType))

(def double-type (gd/get-type-const :doubleType))

(def byte-type (gd/get-type-const :byteType))

(def boolean-type (gd/get-type-const :booleanType))

(def void-type (gd/get-type-const :voidType))
(defn alloc-typed [id type init-len initial-val byte-code-inst & params]

    (case type
      byte-type (do
                  (let [add-parms-count (alength params)]
                    (if (= 0 add-parms-count 0)
                      (let [size (aget params 0)
                            storage (byte-array size)]
                        [id storage]
                        ))))
      int-type (let [storage (.Integer initial-val)]
                 [id storage])
      long-type (let [storage (.Long initial-val)]
                  [id storage])
      string-type (let [storage (.String initial-val)]
                    [id storage])
      float-type (let [storage (.Float initial-val)]
                   [id storage])
      double-type (let [storage (.Double initial-val)]
                    [id storage])
      ))

  (defn free [id storage-map]
    (dissoc storage-map id)
    )

  (defn typed-copy-SS [type source]
    (case type
      int-type (.Integer source)
      long-type (.Long source)
      string-type (let [new-string (.String source)]
                    new-string)
      float-type (.Float source)
      double-type (.Double source)
      byte-type (let [len (alength source)
                      new-arr (byte-array len)]
                  (ru/arr-copy new-arr source))))

