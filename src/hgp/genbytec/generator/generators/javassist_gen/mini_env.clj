(ns hgp.genbytec.generator.generators.javassist-gen.mini-env)

(def const-pool (gensym "const-pool"))
(def class-pool (gensym "class-pool"))
(def address-table (atom {}))
(def pool-table (atom {}) )


(defn add-address [key address]
  (let [pair {key address}]
    (swap! address-table conj pair)
    pair))

(defn remove-address [key]
    (swap! address-table dissoc key)
  key)

(defn get-address [key]
  (get @address-table key))

(defn get-pools []
  [(get @pool-table const-pool)  (get @pool-table class-pool)])

(defn add-pools [const-pool-inst class-pool-inst]
 (swap! @pool-table conj {const-pool const-pool-inst})
   (swap! @pool-table conj {const-pool const-pool-inst}))
