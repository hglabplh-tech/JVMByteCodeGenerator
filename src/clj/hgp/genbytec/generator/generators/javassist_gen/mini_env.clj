(ns hgp.genbytec.generator.generators.javassist-gen.mini-env
  (:import (javassist ClassPool)))

(def const-pool (gensym "const-pool"))
(def class-pool (gensym "class-pool"))
(def address-table (atom {}))
(def pool-table (atom {}) )


(defn add-address [key address]
  (let [pair {key address}]
    (swap! address-table conj pair)
    pair))
(defn add-current-address [byte-code-inst]
  (let [key (gensym "address-current")
        address (.currentPc byte-code-inst)
        pair {key address}]
    (swap! address-table conj pair)
    key
    ))

(defn add-address-simple [address]
  (let [key (gensym "address-current")
        pair {key address}]
    (swap! address-table conj pair)
    key
    ))

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

;; 370 ASS definitions
;;cc	 relationship 	 mask-bits
(def equal-zero-mask-cc 2r1000)
(def low-minus-mask-cc	2r0100)
(def high-plus-mask-cc	2r0010)
(def overflow-mask-cc	2r0001)

(defn retrieve-main-class []
  (let [class-pool (ClassPool/getDefault)
        main-class (.get class-pool "genbytecj.templates.MainClassTemplate")]
    main-class))
