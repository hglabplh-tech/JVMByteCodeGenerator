(ns hgp.genbytec.generator.generators.starter
  (:require [hgp.genbytec.generator.generators.javassist-gen.mini-env :as env]
            [hgp.genbytec.generator.generators.javassist-gen.class-gen :as cg])
  (:import (javassist.bytecode ConstPool)))

(defn start-with-env []
  (let [[class-pool-inst main-class] (cg/def-main-class)
        const-pool-inst (ConstPool. (.getName main-class))]
    (env/add-pools const-pool-inst class-pool-inst)))
