(ns hgp.genbytec.generator.generators.javassist-gen.op-logic
  (:require [hgp.genbytec.generator.generators.javassist-gen.mini-env :as env])
  (:import (javassist.bytecode Opcode Bytecode ConstPool)
           (javassist ClassPool))
  )

(defn new-byte-node [my-class]
  (let [[const-pool class-pool] (env/get-pools)
        byte-code-inst (Bytecode. const-pool)]
    [const-pool byte-code-inst]
    ))



(defn op-goto [key this-class]
  ^{:doc "defines a goto to a specified address out of the address table "}
  ;; here is the goto target
  ;; int pc = bytecode.currentPc();
  ;; here is the goto
  ;;bytecode.addOpcode(Opcode.GOTO);
  ;;pc2 = bytecode.currentPc();
  ;;bytecode.addIndex(0);

  (let [[const-pool byte-code-inst] (new-byte-node this-class)]
    (.addOpcode byte-code-inst (Opcode/GOTO))
    (let [pc (env/get-address key)]
      (.addIndex byte-code-inst (- (.currentPc byte-code-inst) (+ pc 1)))
      [const-pool byte-code-inst])))

