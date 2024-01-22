(ns hgp.genbytec.generator.generators.javassist-gen.machine-part.register-utils)


(defn arr-copy [target source]
  (let [arr-len (alength source)]
    (loop [x (- arr-len 1)]
      (aset target x (aget source x))
      (recur (- x 1)))
    target
    ))
(defn get-register [reg-map reg]
  (get reg-map reg))

(defn load-to-reg [reg-map reg value]
  (let [reg-to-load (get-register reg-map reg)]
  (arr-copy reg-to-load value)
    ))

;; next is store from reg
(defn store-from-reg [reg-map value reg]
  (let [reg-to-load (get-register reg-map reg)]
    (arr-copy value reg-to-load)
    )
  )
;; load reg to reg

(defn reg-to-reg [reg-map reg-target reg-source]
  (let [reg-target-load (get-register reg-map reg-target)
        reg-source-get (get-register reg-map reg-source)]
    (arr-copy reg-target-load reg-source-get)
    )
  )
