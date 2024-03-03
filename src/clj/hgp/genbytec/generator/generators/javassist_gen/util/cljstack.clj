(ns hgp.genbytec.generator.generators.javassist-gen.util.cljstack)
(defprotocol IStack
  (printStack [this])
  (push [this value])
  (pop [this])
  (peek [this]))
(defrecord Stack [storage] IStack
  (printStack [this]
    (println storage))
  (push [this value]
    (swap! storage conj value))
  (pop [this]
    (swap! storage clojure.core/pop))
  (peek [this]
    (clojure.core/peek @storage)))
(defn new-op-stack []
  (Stack. (atom '())))
