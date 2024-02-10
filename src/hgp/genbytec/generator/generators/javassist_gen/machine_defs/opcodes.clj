(ns hgp.genbytec.generator.generators.javassist-gen.machine-defs.opcodes)
(def op-codes-def
  {:nop            [0 1]
   :aconst_null    [1 1]
   :iconst_m1      [2 1]
   :iconst_0       [3 1]
   :iconst_1       [4 1]
   :iconst_2       [5 1]
   :iconst_3       [6 1]
   :iconst_4       [7 1]
   :iconst_5       [8 1]
   :lconst_0       [9 1]
   :lconst_1       [10 1]
   :fconst_0       [11 1]
   :fconst_1       [12 1]
   :fconst_2       [13 1]
   :dconst_0       [14 1]
   :dconst_1       [15 1]
   :bipush         [16 2]
   :sipush         [17 3]
   :ldc            [18 2]
   :ldc_w          [19 3]
   :ldc2_w         [20 3]
   :iload          [21 2]
   :lload          [22 2]
   :fload          [23 2]
   :dload          [24 2]
   :aload          [25 2]
   :iload_0        [26 1]
   :iload_1        [27 1]
   :iload_2        [28 1]
   :iload_3        [29 1]
   :lload_0        [30 1]
   :lload_1        [31 1]
   :lload_2        [32 1]
   :lload_3        [33 1]
   :fload_0        [34 1]
   :fload_1        [35 1]
   :fload_2        [36 1]
   :fload_3        [37 1]
   :dload_0        [38 1]
   :dload_1        [39 1]
   :dload_2        [40 1]
   :dload_3        [41 1]
   :aload_0        [42 1]
   :aload_1        [43 1]
   :aload_2        [44 1]
   :aload_3        [45 1]
   :iaload         [46 1]
   :laload         [47 1]
   :faload         [48 1]
   :daload         [49 1]
   :aaload         [50 1]
   :baload         [51 1]
   :caload         [52 1]
   :saload         [53 1]
   :istore         [54 2]
   :lstore         [55 2]
   :fstore         [56 2]
   :dsore          [57 2]
   :astore         [58 2]
   :istore:0       [59 1]
   :istore_1       [60 1]
   :istore_2       [61 1]
   :istore_3       [62 1]
   :lstore_0       [63 1]
   :lstore_1       [64 1]
   :lstore_2       [65 1]
   :lstore_3       [66 1]
   :fstore_0       [67 1]
   :fstore_1       [68 1]
   :fstore_2       [69 1]
   :fstore_3       [70 1]
   :dstore_0       [71 1]
   :dstore_1       [72 1]
   :dstore_2       [73 1]
   :dstore_3       [74 1]
   :astore_0       [75 1]
   :astore_1       [76 1]
   :astore_2       [77 1]
   :astore_3       [78 1]
   :iastore        [79 1]
   :lastore        [80 1]
   :fastore        [81 1]
   :dastore        [82 1]
   :aastore        [83 1]
   :bastore        [84 1]
   :castore        [85 1]
   :sastore        [86 1]
   :pop            [87 1]
   :pop2           [88 1]
   :dup            [89 1]
   :dup_x1         [90 1]
   :dup_x2         [91 1]
   :dup2           [92 1]
   :dup2_x1        [93 1]
   :dup2_x2        [94 1]
   :swap           [95 1]
   :iadd           [96 1]
   :ladd           [97 1]
   :fadd           [98 1]
   :dadd           [99 1]
   :isub           [100 1]
   :lsub           [101 1]
   :fsub           [102 1]
   :dsub           [103 1]
   :imul           [104 1]
   :lmul           [105 1]
   :fmul           [106 1]
   :dmul           [107 1]
   :idiv           [108 1]
   :ldiv           [109 1]
   :fdiv           [110 1]
   :ddiv           [111 1]
   :irem           [112 1]
   :lrem           [113 1]
   :frem           [114 1]
   :drem           [115 1]
   :ineg           [116 1]
   :lneg           [117 1]
   :fneg           [118 1]
   :dneg           [119 1]
   :ishl           [120 1]
   :lshl           [121 1]
   :ishr           [122 1]
   :lshr           [123 1]
   :iushr          [124 1]
   :lushr          [125 1]
   :iand           [126 1]
   :land           [127 1]
   :ior            [128 1]
   :lor            [129 1]
   :ixor           [130 1]
   :lxor           [131 1]
   :iinc           [132 3]
   :i2l            [133 1]
   :i2f            [134 1]
   :i2d            [135 1]
   :l2i            [136 1]
   :l2f            [137 1]
   :l2d            [138 1]
   :f2i            [139 1]
   :f2l            [140 1]
   :f2d            [141 1]
   :d2i            [142 1]
   :d2l            [143 1]
   :d2f            [144 1]
   :i2b            [145 1]
   :i2c            [146 1]
   :i2s            [147 1]
   :lcmp           [148 1]
   :fcmpl          [149 1]
   :fcmpg          [150 1]
   :dcmpl          [151 1]
   :dcmpg          [152 1]
   :ifeq           [153 3]
   :ifne           [154 3]
   :iflt           [155 3]
   :ifge           [156 3]
   :ifgt           [157 3]
   :ifle           [158 3]
   :if_cmp_eq      [159 3]
   :if_icmpne      [160 3]
   :if_icmplt      [161 3]
   :if_icmpge      [162 3]
   :if_icmpgt      [163 3]
   :if_icmple      [164 3]
   :if_acmpeq      [165 3]
   :if_acmpne      [166 3]
   :goto           [167 3]
   :jsr            [168 3]
   :ret            [169 2]
   :tableswitch    [170 0]
   :lookupswitch   [171 0]
   :ireturn        [172 1]
   :lreturn        [173 1]
   :freturn        [174 1]
   :dreturn        [175 1]
   :areturn        [176 1]
   :return         [177 1]
   :getstatic      [178 3]
   :putstatic      [179 3]
   :getfield       [180 3]
   :putfield       [181 3]
   :invokevirtual  [182 3]
   :invokespecial  [183 3]
   :invokestatic   [184 3]
   :invokeintrface [185 5]
   :invokedynamic  [186 5]
   :new            [187 3]
   :newarray       [188 2]
   :anewarray      [189 3]
   :arraylength    [190 1]
   :athrow         [191 1]
   :checkcast      [192 3]
   :instanceof     [193 3]
   :monitorenter   [194 1]
   :monitorexit    [195 1]
   :wide           [196 0]
   :multianewarray [197 4]
   :ifnull         [198 3]
   :ifnonnull      [199 3]
   :goto_w         [200 5]
   :jsr_w          [201 5]
   })
