(ns hgp.genbytec.generator.generators.javassist-gen.test-op-logic
  (:require [clojure.test :refer :all]
            [hgp.genbytec.generator.generators.javassist-gen.general-defs :as gd]
            [hgp.genbytec.generator.generators.javassist-gen.mini-env :as env]
            [hgp.genbytec.generator.generators.javassist-gen.method-gen-add :as mg]
            [hgp.genbytec.generator.generators.javassist-gen.class-gen :as cg]
            [hgp.genbytec.generator.generators.javassist-gen.op-logic :as ol])
  (:import (java.lang Object)
           (javassist.bytecode Bytecode)))


(deftest add-goto-test []
                       (let
                         [addr (env/add-address :label-1 7000)
                          [const-pool byte-code-inst] (ol/op-goto :label-1 "java.lang.Object")]
                         (.print const-pool)
                         (println (.getStackDepth byte-code-inst))))