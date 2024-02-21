(ns hgp.genbytec.generator.generators.javassist-gen.acc-mod-defs
  (:import
    (jasmin.utils.jas RuntimeConstants)))

;; in general for fields
(def public-static-final (and RuntimeConstants/ACC_PUBLIC RuntimeConstants/ACC_STATIC RuntimeConstants/ACC_FINAL))

;; for normal classes
(def public-class RuntimeConstants/ACC_PUBLIC)

(def final-class (and public-class RuntimeConstants/ACC_FINAL))

;; for inner classes
(def  public-static-class (and RuntimeConstants/ACC_STATIC RuntimeConstants/ACC_PUBLIC))

;; mehthods
(def public-method public-class)
(def public-static-method public-static-class)

(def private-method RuntimeConstants/ACC_PRIVATE)

(def private-static-method (and private-method RuntimeConstants/ACC_STATIC))
