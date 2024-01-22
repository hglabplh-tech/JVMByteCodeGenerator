(ns hgp.genbytec.generator.generators.javassist-gen.machine-part.storage-utils
  (:require [hgp.genbytec.generator.generators.javassist-gen.machine-part.register-utils :as ru]))

(def int-type 0x01)

(def long-type 0x02)

(def string-type 0x04)

(def float-type 0x08)

(def double-type 0x10)

(def byte-type 0x20)

(def complex-type 0x40)

(defn alloc-typed [id type initial-val & params]
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

