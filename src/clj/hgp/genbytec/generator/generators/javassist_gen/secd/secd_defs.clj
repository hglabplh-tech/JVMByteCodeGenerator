(ns hgp.genbytec.generator.generators.javassist-gen.secd.secd-defs
  (:require [active.clojure.record-spec :refer [define-record-type]]
            [hgp.genbytec.generator.generators.javassist-gen.secd.secd-core-lang :as cl]
            ))



(define-record-type signature
                    (make-signature sig-type ref-value)
                    signature?
                    [sig-type signature-sig-type
                     ref-value signature-ref-value] )
; Ein Frame besteht aus:
; - Stack
; - Umgebung
; - Code

(defn base?
 [term]
 (or (boolean? term) (number? term)))

(def base (make-signature 'predicate base?))

(define-record-type frame
               (make-frame stack environment machine-code)
                    frame?
               [stack frame-stack
                environment frame-environment
                machine-code frame-machine-code
                ])
(def dump (make-signature 'list-of frame))

; Ein SECD-Wert ist ein Basiswert oder eine Closure
          ;; think about a real signature

; Eine Closure besteht aus:
; - Variable
; - Code
; - Umgebung
(define-record-type closure
               (make-closure sym machine-code environment)
               closure?
               [sym closure-variable
                machine-code closure-code
                environment  closure-environment ] )

(def value (make-signature 'base closure?))

(define-record-type env-binding
               (make-env-binding sym var-val)
                    env-binding?
               [sym env-binding-variable
                var-val env-binding-var-val] )

; Ein SECD-Zustand besteht aus:
; - Stack
; - Umgebung
; - Code
; - Dump
(define-record-type secd
               (make-secd stack environment machine-code dump-env)
               secd?
               [stack secd-stack
                environment secd-environment
                machine-code secd-code
                dump-env secd-dump
                ]
               )

; Eine Maschinencode-Programm ist eine Liste von Instruktionen.



; Applikations-Instruktion
(define-record-type ap
               (make-ap )
                    ap?
                    [a ap-a])

; Eine endrekursive Applikations-Instruktion ist ein Wert
;   (make-tailap)
(define-record-type tailap
               (make-tailap)
                    tailap?
                    [a tailap-a])

; Eine Instruktion für eine primitive Applikation hat folgende
; Eigenschaften:
; - Operator
; - Stelligkeit
(define-record-type prim
               (make-prim sym natural)
                    prim?
              [sym prim-operator
               natural prim-arity] )

; Eine Abstraktions-Instruktion hat folgende Eigenschaften:
; - Parameter (eine Variable)
; - Code für den Rumpf
(define-record-type abst
               (make-abst sym machine-code)
                    abs?
               [sym abst-variable
                machine-code abst-code]  )




; Eine Instruktion ist eins der folgenden:
; - ein Basiswert
; - eine Variable
; - eine Applikations-Instruktion
; - eine endrekursive Applikations-Instruktion
; - eine Instruktion für eine primitive Applikation
; - eine Abstraktion


; Ein Lambda-Term ist eins der folgenden:
; - ein Symbol (für eine Variable)
; - eine zweielementige Liste (für eine reguläre Applikation)
; - eine Liste der Form (lambda (x) e) (für eine Abstraktion)
; - ein Basiswert
; - eine Liste mit einem Primitivum als erstem Element
;      (für eine primitive Applikation)

(def sym (make-signature 'predicat symbol?))


(defn abstraction?
        [term]
  (and (list? term)
       (= 'lambda (first term))))
(def abstraction (make-signature 'predicate abstraction?))
(defn application?   [term]
                (and (list? term)
                     (not (= 'lambda (first term)))
                     (not (cl/primitive? (first term)))))

(def application (make-signature 'predicate application?))

(defn primitive-application?
  [term]
                (and (list? term)
                     (cl/primitive? (first term))))

(def primitive-application (make-signature 'predicate primitive-application?))

(def term
        (make-signature
          'compound
          [sym
           application
           abstraction
           primitive-application
           base]))
(def instruction
  (make-signature
    'compound
    [base
     sym
     ap
     tailap
     prim
     abst] ))

(def machine-code (make-signature 'list-of instruction))


