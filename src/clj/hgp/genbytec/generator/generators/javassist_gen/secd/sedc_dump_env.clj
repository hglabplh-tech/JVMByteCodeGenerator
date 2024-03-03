(ns hgp.genbytec.generator.generators.javassist-gen.secd.sedc-dump-env
  (:require [hgp.genbytec.generator.generators.javassist-gen.util.cljstack :as stack]
            [hgp.genbytec.generator.generators.javassist-gen.secd.secd-defs :as defs]))






(def machine-dump (atom ()))
(defn fresh-frame []
  (let [fresh-stack (stack/new-op-stack)
        fresh-env (atom {})
        fresh-code (atom {})
        frame (defs/make-frame fresh-stack fresh-env fresh-code)]
    (swap!  machine-dump conj frame)
    frame
    ))


(fresh-frame)
(def act-machine-env (defs/frame-environment (first machine-dump)))
(def op-stack (defs/frame-stack (first machine-dump)))

(def act-code (defs/frame-machine-code (first machine-dump)))



(defn add-frame-to-dump [stack env code]
  (let [frame (defs/make-frame stack env code)]
    (swap!  machine-dump conj frame)
    frame
    ))

(defn remove-frame-from-dump [stack env code]
  (let [frame (defs/make-frame stack env code)]
    (swap!  machine-dump dissoc frame)
    frame
    ))

(defn add-reference [var-ref var-val]
(let [descriptor (defs/make-env-binding var-ref var-val)
      pair {var-ref descriptor}]
  (swap!(defs/frame-environment (first machine-dump)) conj pair)
  pair))

(defn remove-reference [var-ref]
  (swap! (defs/frame-environment (first machine-dump)) dissoc var-ref)
  var-ref)

(defn lookup-act-reference [var-ref]
  (let [result (get @act-machine-env var-ref)]
    (if (defs/env-binding? result)
      result
      )
    ))

(defn lookup-reference [var-ref]
  (loop [env-to-lookup (defs/frame-environment (first machine-dump))]
    (let [result (get @env-to-lookup var-ref)]
      (if env-to-lookup
    (if (defs/env-binding? result)
      result
      (recur (defs/frame-environment  (first (rest machine-dump)))))
      )
    )))