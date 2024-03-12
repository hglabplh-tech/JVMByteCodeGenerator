(ns hgp.genbytec.generator.generators.javassist-gen.secd.secd-compile-test
  (:require [clojure.test :refer :all]))


(def test-code-snippet (:invoke
                         (:lambda ('y)
                          (:lambda ('x)
                            ('* [:var-ref 'y]
                              [:var-ref 'x]))
                                        [:const 4])
                        [:const 5])
  )
(def call-it (:invoke [:abstract-ref 'adder] ))

