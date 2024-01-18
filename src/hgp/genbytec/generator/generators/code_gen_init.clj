(ns hgp.genbytec.generator.generators.code-gen-init
  (:import (genbytecj.generator.model.startpoint GenerationController)
           (genbytecj.generator.model.utils Randomizer RandomSupplier)
           ))

(defn new-code-gen-high-level-gen []
  (let [gen-controler (GenerationController.)
        rand-code-gen (.initRandCodeGen gen-controler)
        field-var-generator (.getFieldVarGenerator rand-code-gen)
        method-generator (.getMethodGenerator rand-code-gen)
        math-generator (.getMathGenerator rand-code-gen)
        snippet-generator (.getSnippetGenerator rand-code-gen)
        type-cast-generator (.getTypeCastGenerator rand-code-gen)
        control-flow-generator (.getControlFlowGenerator rand-code-gen)
        array-access-generator (.getArrayAccessGenerator rand-code-gen)]
    {:field-var-gen    field-var-generator  :method-gen method-generator
     :math-gen         math-generator       :snippet-gen snippet-generator
     :type-cast-gen    type-cast-generator  :control-flow-gen control-flow-generator
     :array-access-gen array-access-generator}))

(defn new-jass-code-gen[]
  )




