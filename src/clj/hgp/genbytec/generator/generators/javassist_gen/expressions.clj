(ns hgp.genbytec.generator.generators.javassist-gen.expressions
  (:require [hgp.genbytec.generator.generators.javassist-gen.method-gen-add :as  gmeth]
            [hgp.genbytec.generator.generators.javassist-gen.tokens :as tok]
            [hgp.genbytec.generator.generators.javassist-gen.insn-simplified :as insn]
            [hgp.genbytec.generator.generators.javassist-gen.mini-env :as env]
            [hgp.genbytec.generator.generators.javassist-gen.general-defs :as defs]
  ))

(def precedence
  {:not 20
   :mult 15
   :div  15
   :mod  15
   :plus 10
   :minus 10})

(defn get-precedence [tok]
  (let [prec (get precedence tok)
        prec (if prec
               prec
               2)]) )
(defn pushWorkIntegerVals [code-attr first-val-idx second-val-idx result-idx op-code]
  (insn/iload-to-code-attr code-attr first-val-idx)
  (insn/iload-to-code-attr code-attr second-val-idx)
  (insn/opcode-to-codeattr code-attr op-code)
  (insn/istore-to-code-attr code-attr result-idx)
  )

(defn pushWorkLongVals [code-attr first-val-idx second-val-idx result-idx op-code]
  (insn/lload-to-code-attr code-attr first-val-idx)
  (insn/lload-to-code-attr code-attr second-val-idx)
  (insn/opcode-to-codeattr code-attr op-code)
  (insn/lstore-to-code-attr code-attr result-idx)
  )

(defn pushWorkFloatVals [code-attr first-val-idx second-val-idx result-idx op-code]
  (insn/fload-to-code-attr code-attr first-val-idx)
  (insn/fload-to-code-attr code-attr second-val-idx)
  (insn/opcode-to-codeattr code-attr op-code)
  (insn/fstore-to-code-attr code-attr result-idx)
  )

(defn pushWorkDoubleVals [code-attr first-val-idx second-val-idx result-idx op-code]
  (insn/dload-to-code-attr code-attr first-val-idx)
  (insn/dload-to-code-attr code-attr second-val-idx)
  (insn/opcode-to-codeattr code-attr op-code)
  (insn/dstore-to-code-attr code-attr result-idx)
  )

(defn detect-next-tok [next-tok]
  (let [is-number (number? next-tok)
        is-math-token (= (tok/math-token next-tok) :math-token)
        is-other-token (= (tok/math-token next-tok) :other-token)]
    (if is-math-token
      :math-token
      (if is-other-token
        (if (contains? [:lparen :rparen] next-tok)
          :parantheses
          (if is-number
            :number
            :function)))))
  )

(defn process-math [rest-expression]

  )
(defn parse-expression [code-attr expr-vect]
  (let [first-op (first expr-vect)
        operator (second expr-vect)
        rest-vect (rest (rest expr-vect))
        next-tok (first rest-vect)]
    (case (detect-next-tok next-tok)
      :number (insn/iload-to-code-attr code-attr next-tok)
      :math-token
      :parantheses
      :function
      )
    ))


(def term [9 :mult :lparen 5 :plus 7 :minus 3 :rparen ])
