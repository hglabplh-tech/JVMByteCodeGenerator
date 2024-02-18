(ns hgp.genbytec.generator.generators.javassist-gen.util.methods-util
  (:require [hgp.genbytec.generator.generators.javassist-gen.mini-env :as env]
            [hgp.genbytec.generator.generators.javassist-gen.general-defs :as defs])
  (:import (javassist CtMethod CtPrimitiveType )
           (javassist.bytecode Bytecode Opcode)
           (javassist.compiler JvstCodeGen)))




(defn make-modifiers [& modifiers]
  (loop [mod modifiers result 0]
    (if modifiers
      (recur (next modifiers) (bit-or result mod))
      result)))

(defn gen-byte-code [code-gen-fun]
  (let
    [[const-pool class-pool] (env/get-pools)
     byte-code  (Bytecode. const-pool 0 0)]
    (code-gen-fun byte-code) byte-code) )

(defn new-byte-code []
  (let
    [[const-pool class-pool] (env/get-pools)
     byte-code  (Bytecode. const-pool 0 0)]
     byte-code) )

(defn- compileReturn [byte-code-inst ct-class]
  (if (.isPrimitive ct-class)
    (do (let [pt (cast CtPrimitiveType ct-class)]
          (if (not (= pt (get defs/type-constants :voidType)))
            (do (let [meth-name (.getGetMethodName pt)]
                  (.addCheckcast byte-code-inst meth-name)
                  (.addInvokevirtual byte-code-inst meth-name
                                     (.getGetMethodName pt)
                                     (.getGetMehodDescriptor pt))
                  )


            ))
          (.addOpcode byte-code-inst (.getReturnOp pt))
          )
      )
    (do (.addCheckcast byte-code-inst ct-class)
        (.addOpcode byte-code-inst (Opcode/ARETURN))
      )))



(defn create-method-byte-code [return-type meth-name parameter-types declaring-clazz modifiers code-gen-fun is-static]
  (let [new-method (CtMethod. return-type meth-name parameter-types
                              declaring-clazz)
        dummy (.setModifiers new-method (make-modifiers modifiers))
        byte-code-inst (gen-byte-code code-gen-fun)]

        (if (not is-static)
          (.addALoad byte-code-inst 0))

        (let [stacksize (JvstCodeGen/compileParameterList
                          byte-code-inst
                          parameter-types
                          (if is-static 0 1))
              stacksize2 0]
          ;;          (if is-static
          ;;(.addInvokestatic byte-code-inst (.THIS byte-code-inst) body-name, "desc") ;; change call
          ;;(.addInvokespecial byte-code-inst (.THIS byte-code-inst) body-name, "desc") ;; change call
          ;;)
          (compileReturn byte-code-inst return-type)
          (if (< stacksize (+ stacksize2 2))
            (+ stacksize2 2)
            stacksize))))

(defn add-invoke-meth-virtual [byte-code-inst theClazz]
  (let [primitive (cast CtPrimitiveType theClazz)
        methName (.getMethodName primitive)
        methDescriptor (.getGetMethodDescriptor primitive)]
    (.addInvokevirtual byte-code-inst primitive methName methDescriptor)
    ))

(defn add-invoke-meth-dynamic [byte-code-inst theClazz]
  (let [primitive (cast CtPrimitiveType theClazz)
        methName (.getMethodName primitive)
        methDescriptor (.getGetMethodDescriptor primitive)]
    (.addInvokedynamic byte-code-inst primitive methName methDescriptor)
    ))

(defn add-invoke-meth-static [byte-code-inst theClazz]
  (let [primitive (cast CtPrimitiveType theClazz)
        methName (.getMethodName primitive)
        methDescriptor (.getGetMethodDescriptor primitive)]
    (.addInvokestatic byte-code-inst primitive methName methDescriptor)
    ))
