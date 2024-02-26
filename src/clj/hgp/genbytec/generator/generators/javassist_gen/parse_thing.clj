(ns hgp.genbytec.generator.generators.javassist-gen.parse-thing
  (:require [hgp.genbytec.generator.generators.javassist-gen.tokens :as tok]))

(def test-expr {:returnType :void
                :lambda     "lambdaFun"
                :paramList  [:any :any :any :any :long]})

(defn map-parameter-types [param-types]
  (let [loop-params param-types]
    (loop [param-lst loop-params
           real-types []]
      (if param-lst
        (let [param (first param-lst)]
          (recur (next param-lst)
                 (conj real-types
                       (get tok/token-to-num-type param))))
        real-types))))




(defn parse [fun-def]
  (let [return-type (get tok/token-to-num-type (get fun-def :returnType))
        name (get fun-def :lambda)
        param-list (get fun-def :paramList)
        real-params (map-parameter-types param-list)]
    [name return-type real-params]
    ))

(let [[name return-type real-params ] (parse test-expr)]
  (println name)
  (println return-type)
  (println real-params)
  )
