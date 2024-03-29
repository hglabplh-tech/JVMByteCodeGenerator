(ns hgp.genbytec.generator.generators.javassist-gen.class-gen
  (:require [hgp.genbytec.generator.generators.javassist-gen.mini-env :as env])
  (:import (javassist ClassPool CtMethod CtNewMethod CtClass)
           (java.lang Class)) )
;;evalClass.addField
;;
;;(  CtField.make ("private java.util.Random rnd;", evalClass)) ;

;;CtConstructor constr = CtNewConstructor.defaultConstructor (evalClass) ;
;;constr.setBody ("{ rnd = new java.util.Random(System.currentTimeMillis()); }") ;
;;evalClass.addConstructor (constr)                           ;

;;evalClass.addMethod
;; CtNewMethod.make ("public double rnd() { return rnd.nextDouble(); }", evalClass)) ;
(defn new-clazz [clazz-name]
  (let [[const-pool class-pool] (env/get-pools)
        clazz (.makeClass class-pool clazz-name)
        ]
   [class-pool const-pool clazz]))

(defn retrieve-main-class []
  (let [class-pool (ClassPool/getDefault)
        main-class (.get class-pool "genbytecj.templates.MainClassTemplate")]
    main-class))