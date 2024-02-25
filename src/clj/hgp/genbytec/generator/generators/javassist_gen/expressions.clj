(ns hgp.genbytec.generator.generators.javassist-gen.expressions
  (:require [hgp.genbytec.generator.generators.javassist-gen.method-gen-add :as  gmeth]
            [hgp.genbytec.generator.generators.javassist-gen.insn-simplified :as insn]
            [hgp.genbytec.generator.generators.javassist-gen.mini-env :as env]
            [hgp.genbytec.generator.generators.javassist-gen.general-defs :as defs]
  ))
(def tokens
  [:plus :minus :mult :div :mod :sqrt :exp :lparen :rparen :eqs :assign :gt :lt :ge :le :equal
   :int :long :float:double :short :byte ])