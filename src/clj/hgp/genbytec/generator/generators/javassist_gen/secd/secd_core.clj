(ns hgp.genbytec.generator.generators.javassist-gen.secd.secd-core
  (:require [hgp.genbytec.generator.generators.javassist-gen.util.cljstack :as stack]
            [hgp.genbytec.generator.generators.javassist-gen.secd.secd-defs :as defs]
            [hgp.genbytec.generator.generators.javassist-gen.secd.sedc-dump-env :as env]))



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
         token (if (or (defs/primitive? seq-order-list) (defs/ap? seq-order-list))
                 seq-order-list
                 (first seq-order-list))]
    (cond
      (defs/base? token)
      (do (stack/push env/op-stack token)
          (defs/make-secd env/op-stack
                          env/act-machine-env
                          (rest seq-order-list)
                          env/machine-dump))
      (symbol? token)
      (do (stack/push env/op-stack token)
          [token])
      (defs/prim? token)
      (do
      (defs/make-secd (cons
                        (apply-primitive (prim-operator token)
                                         (take-reverse (prim-arity token) stack))
                        (drop (prim-arity token) env/op-stack))
                      environment
                      (rest seq-order-list)
                      dump))
      (defs/abs? token)
      (do  (defs/make-secd (cons (defs/make-closure (abst-variable token)
                                               (abst-code token)
                                               environment)
                            stack)
                      environment
                      (rest seq-order-list)
                      dump))
      (ap? token)
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
      (tailap? token)
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
           (if (or (defs/primitive? (first (rest complete-list))) (defs/ap? (first (rest complete-list))))
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


