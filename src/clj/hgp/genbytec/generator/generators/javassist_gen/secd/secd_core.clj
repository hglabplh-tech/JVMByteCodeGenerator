(ns hgp.genbytec.generator.generators.javassist-gen.secd.secd-core
  (:require [hgp.genbytec.generator.generators.javassist-gen.util.cljstack :as stack]
            [hgp.genbytec.generator.generators.javassist-gen.secd.secd-defs :as defs]
            [hgp.genbytec.generator.generators.javassist-gen.secd.secd-dump-env :as env]
            [hgp.genbytec.generator.generators.javassist-gen.secd.secd-core-lang :as cl]
            [hgp.genbytec.generator.generators.javassist-gen.secd.secd-bytecode :as bcode]))



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
(defn trace-code [inst-and-values]
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
               binding-type ()]
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
      (cl/abstract? token)
      (do  (defs/make-secd (cons (defs/make-closure (abst-variable token)
                                               (abst-code token)
                                               environment)
                            stack)
                      environment
                      (rest seq-order-list)
                      dump))
      (env/ap? token)
      (do (let [closure (stack/pop env/op-stack)
                on-stack  (stack/peek env/op-stack)
                ]
            (env/add-reference  (closure-environment closure) (closure-variable closure))
            (env/remove-reference on-stack)
            (env/add-frame-to-dump env/op-stack
                                   env/act-machine-env (rest seq-order-list))
            (defs/make-secd env/op-stack
                            env/act-machine-env
                            (rest seq-order-list)
                            env/machine-dump)
            ))
      (env/tailap? token)
      (do (let [closure (stack/pop env/op-stack)
                on-stack  (stack/peek env/op-stack)
                ]
            (env/add-reference  (closure-environment closure) (closure-variable closure))
            (env/remove-reference on-stack)
            (env/add-frame-to-dump env/op-stack
                                   env/act-machine-env (rest seq-order-list))
            (defs/make-secd env/op-stack
                            env/act-machine-env
                            (rest seq-order-list)
                            env/machine-dump)
            ))
      (empty? code)
      (let [frame (first dump)]
        (make-secd
          (cons (first stack)
                (frame-stack frame))
          (frame-environment frame)
          (frame-code frame)
          (rest dump)))
      )


    (recur (rest complete-list) (first (rest complete-list))
           (if (or (cl/primitive? (first (rest complete-list))) (defs/ap? (first (rest complete-list))))
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


