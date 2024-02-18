(ns hgp.genbytec.generator.generators.javassist-gen.machine-part.register-utils
  (:require [hgp.genbytec.generator.generators.javassist-gen.machine-part.opcodes-and-regs-hex
              :as codes]))

(def base-regs (atom {codes/R0 (int 0) codes/R1 (int 0)
                      codes/R2 (int 0) codes/R3 (int 0)
                      codes/R4 (int 0) codes/R5 (int 0)
                      codes/R6 (int 0)  codes/R7 (int 0)
                      codes/R8 (int 0)  codes/R9 (int 0)
                      codes/R10 (int 0) codes/R11 (int 0)
                      codes/R12 (int 0) codes/R13 (int 0)
                      codes/R14 (int 0) codes/R15 (int 0)}))

(defn get-register [reg]
  (get @base-regs reg))

(defn load-to-reg [reg-map reg value]
  (let [reg-to-load (get-register reg)]
  (swap! base-regs conj {reg value})
    ))

;; next is store from reg
(defn store-from-reg [reg-map value reg]
  (let [reg-to-load (get-register  reg)]

    )
  )
;; load reg to reg

(defn reg-to-reg [reg-map reg-target reg-source]
  (let [
        reg-source-get (get-register reg-source)]
    (swap! base-regs conj {reg-target reg-source-get})
    )
  )
