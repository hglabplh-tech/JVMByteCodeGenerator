(ns hgp.genbytec.generator.generators.javassist-gen.secd.secd-core
  (:require [hgp.genbytec.generator.generators.javassist-gen.util.cljstack :as stack]
            [hgp.genbytec.generator.generators.javassist-gen.secd.secd-defs :as defs]
            [hgp.genbytec.generator.generators.javassist-gen.secd.secd-dump-env :as env]
            [hgp.genbytec.generator.generators.javassist-gen.secd.secd-core-lang :as cl]
            [hgp.genbytec.generator.generators.javassist-gen.secd.secd-bytecode :as bcode])
  (:import (javassist CtClass)))




(defn take-reverse
  [n list0]
  ;; (: loop (natural (list-of a) (list-of a) -> (list-of a)))
  ((fn
     [n list acc]
     (cond
       ((zero? n) acc)
       ((pos? n)
        (recur (- n 1) (rest list) (cons (first list) acc)))))
   n list0 '()))
;;
(defn pseudo->bytecode [class-name inst-and-values]
  (loop [complete-list inst-and-values
         seq-order-list (first complete-list)
         token    (first seq-order-list)]
    (cond
      (= token :const)
      (let [[type val] (second seq-order-list)]
          (bcode/push-const type val))
      (cl/reference? token)
      (do
        (bcode/environment-lookup (second seq-order-list))  ;; this function is designeed to put the value already on the stack
        )
      (cl/primitive? token)                                 ;;look for the second parameter
      (let [param (second seq-order-list)
            res-list (rest (rest seq-order-list))
            sec-param (first res-list)]
       (if (cl/reference? param)
         (let [[type val] param
              ]
         (bcode/environment-lookup val))
         (let [[type val] param]
           (bcode/push-const type val))
         )
      (if (cl/reference? sec-param)
        (bcode/environment-lookup sec-param)
        (let [[type val] sec-param]
          (bcode/push-const type val))
        )
       (bcode/execute-op-by-type (first token) type)
       )
      ;; here in abstract? the abstract functions (lambda's) are defined and compiled
      (cl/abstract? token)
      (let [rest-forms (next seq-order-list)
            parm-list (first rest-forms)
            body (next rest-forms)]
        (let [var-def (first parm-list)]
          (if (not (empty? var-def))
            (do (stack/push env/op-stack var-def))
            )
          (bcode/add-closure-code-meth class-name (str (gensym "clojure-meth")) CtClass/intType [CtClass/intType]) ;; TODO: change type handling
          (recur body ((first body) (first (first body))))
             ;; add to environment .....
        )
      ;; in application? ap? the functions (lambdas are called and executed
      (cl/ap? token)
      (let [rest-forms (next seq-order-list)
            reference? (cl/reference?  (first rest-forms))
            body (next rest-forms)]
        (if reference?
          (let [dummy (bcode/environment-lookup val)])  ;; get from env on stack
          (let [static-ref  (recur body ((first body) (first (first body))))
                dummy2 (stack/push env/op-stack val)]                                           ;; put on stack
            )  ;; define and after that call // recursion
          )
        ;; here the invocation follows
        )
      (cl/tailap? token)
        (let [rest-forms (next seq-order-list)
              reference? (cl/reference?  (first rest-forms))
              body (next rest-forms)]
          (if reference?
            (let [dummy (bcode/environment-lookup val)])  ;; get from env on stack
            (let [static-ref  (recur body ((first body) (first (first body))))
                  ]                                           ;; put on stack
              )  ;; define and after that call // recursion
            )
          ;; here the invocation follows
          )
      (empty? token)
      (let [frame (first dump)]
        (make-secd
          (cons (first stack)
                (frame-stack frame))
          (frame-environment frame)
          (frame-code frame)
          (rest dump)))
      )


    (recur (rest complete-list) (first (rest complete-list))
           (if (or (cl/primitive? (first (rest complete-list))) (cl/ap? (first (rest complete-list))))
             (first (rest complete-list))
             (first (first (rest complete-list))))
           )))


(defn apply-primitive
        [primitive-expr args]
                (cond
                  (= primitive-expr '+)
                   (+ (first args) (first (rest args)))
                  (= primitive-expr '-)
                   (- (first args) (first (rest args)))
                  (= primitive-expr '=)
                   (= (first args) (first (rest args)))
                  (= primitive-expr '*)
                   (* (first args) (first (rest args)))
                  (= primitive-expr '/)
                   (/ (first args) (first (rest args)))))


  (defn initCompile [class-name pseudo-code]
    (bcode/make-program-class class-name)
    (pseudo->bytecode pseudo-code)
    )