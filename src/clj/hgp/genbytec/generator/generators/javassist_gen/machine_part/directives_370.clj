(ns hgp.genbytec.generator.generators.javassist-gen.machine-part.directives-370)


(def CSECT (gensym "CSECT"))

(def DSECT (gensym "DSECT"))

(def USING (gensym "USING"))

(def EQU (gensym "EQU"))

(def DC (gensym "DC"))

(def DS (gensym "DS"))

(def directive_to_code
  ^{:doc "Here the table mapping of the directives to symbols used later internally"}
  {"CSECT"  CSECT
   "DSECT"  DSECT
   "USING"  USING
   "EQU"    EQU
   "DC"     DC
   "DS"     DS

   })

(defn get-directive [directive-name]
  (get directive_to_code directive-name))

