(ns hgp.genbytec.generator.generators.javassist-gen.tokens
  (:import (javassist CtClass)))

(def tokens
  [:plus :minus :mult :div :mod :sqrt :exp :lparen :rparen :eqs :assign :gt :lt :ge :le :equal
   :int :long :float :double :any :short :byte :returnType :lambda :paramList])

(def token-map {
                :plus       :plus
                :minus      :minus
                :mult       :mult
                :div        :div
                :mod        :mod
                :sqrt       :sqrt
                :exp        :exp
                :lparen     :lparen
                :rparen     :rparen
                :eqs        :eqs
                :assign     :assign
                :gt         :gt
                :lt         :lt
                :ge         :ge
                :le         :le
                :equal      :equal
                :int        :int
                :long       :long
                :float      :float
                :double     :double
                :any        :any
                :short      :short
                :byte       :byte
                :returnType :returnType
                :lambda     :lambda
                :paramList  :paramList
                })

(def token-to-num-type {
                        :plus       1
                        :minus      2
                        :mult       3
                        :div        4
                        :mod        5
                        :sqrt       6
                        :exp        7
                        :lparen     21
                        :rparen     20
                        :eqs        8
                        :assign     9
                        :gt         10
                        :lt         11
                        :ge         12
                        :le         13
                        :equal      14
                        :int        CtClass/intType
                        :long       CtClass/longType
                        :float      CtClass/floatType
                        :double     CtClass/doubleType
                        :any        "java/lang/Object"
                        :short      CtClass/shortType
                        :byte       CtClass/byteType
                        :void       CtClass/voidType
                        :returnType 15
                        :lambda     16
                        :paramList  17
                        })
