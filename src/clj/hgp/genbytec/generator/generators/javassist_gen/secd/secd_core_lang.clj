(ns hgp.genbytec.generator.generators.javassist-gen.secd.secd-core-lang)
;; here the code is defined and it will look as in the following examples
;; with the sample below we have to remember that we need currying to have
;; more than one parameter in a :lambda
;; (:invoke <reference> [var1, var2, var3 ...])
;; (:lambda [var] body)
;; keywords and operators


(def var-const-types {:long ":long"
                      :integer  ":integer"
                      :float    ":float"
                      :double   ":double"
                      :string   ":string"
                      :utf8     ":utf8"})

(defn var-type? [input]
  (contains? var-const-types input))

(defn var-type->string [input]
  (get var-const-types input))

(def :invoke)
(def :lambda)
(def :defn)
(def :fn)

(def :code-body)
(def fun-impl-keywords [:lambda :defn :fn])

(defn abstract? [token]
  (contains? fun-impl-keywords token))

(def :const)

(def :value)

(def :var-ref)

(def :abstract-ref)

(def ref-keywords (:var-ref :abstract-ref))

(defn reference? [token]
  (contains? ref-keywords token))
(def primitives ['+ '- '/ '* '% '= '< '> 'eq?])

(defn primitive? [token]
  (contains? primitives token))

;; the syntax
(defn ap? [token]
  (= token :invoke))

(def tailap? ap?)

(def primitive-syn [primitive? [reference? '...]])

(def invoke-syn ( :invoke [:abstract-ref reference?]  [reference? '...]))

(def lambda-syn [:lambda [reference?] [:code-body]])
(def code-body-syn ())
(def const-syn [:const [var-type? :value]])
(def defn-syn [:defn  :abstract-ref [reference? '...] '(:code-body)])
(def fn-syn [:fn [reference? '...] '([:code-body])])

(def code-body-syn [invoke-syn lambda-syn fn-syn defn-syn reference? primitive-syn ])
