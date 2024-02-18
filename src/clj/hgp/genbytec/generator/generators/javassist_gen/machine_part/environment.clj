(ns hgp.genbytec.generator.generators.javassist-gen.machine-part.environment
  (:require [hgp.genbytec.generator.generators.javassist-gen.machine-part.opcodes-and-regs-hex :as codes]
            [hgp.genbytec.generator.generators.javassist-gen.machine-part.psw-utils :as pu])
  (:import (java.util Arrays))  )


(def alloc-fun (gensym "alloc-fun"))

(def free-fun (gensym "free-fun"))

(def get-store-fun (gensym "get-store-fun"))

(def psw-init-fun (gensym "psw-init"))
;;TODO next will be the registers
(defn vm-env []
  (let [storage (atom {})
        psw (atom {'p-stat-word (byte-array 8)})
        base-regs (atom {codes/R0 (byte-array 4) codes/R1 (byte-array 4)
                         codes/R2 (byte-array 4) codes/R3 (byte-array 4)
                         codes/R4 (byte-array 4) codes/R5 (byte-array 4)
                         codes/R6 (byte-array 4) codes/R7 (byte-array 4)
                         codes/R8 (byte-array 4) codes/R9 (byte-array 4)
                         codes/R10 (byte-array 4) codes/R11 (byte-array 4)
                         codes/R12 (byte-array 4) codes/R13 (byte-array 4)
                         codes/R14 (byte-array 4) codes/R15 (byte-array 4)})
        alloc (fn  [val] (swap! storage conj val))
        free (fn [key] (swap! storage dissoc key))
        get-store-from-map (fn [address] (get @storage address) )
        psw-init (fn [] (pu/init-psw (get  @psw 'p-stat-word )) @psw)
        manipulate (fn [cmd & args]
                 (cond (= cmd alloc-fun)
                       (alloc (first args))
                       (= cmd free-fun)
                       (free (first args))
                       (= cmd get-store-fun)
                       (get-store-from-map (first args))
                       (= cmd psw-init-fun)
                       (psw-init)
                       ))]
manipulate))

(def vm-environment (vm-env))
(vm-environment psw-init-fun)