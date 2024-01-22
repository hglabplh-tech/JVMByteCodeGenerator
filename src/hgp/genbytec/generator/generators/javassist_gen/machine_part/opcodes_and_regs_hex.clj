(ns hgp.genbytec.generator.generators.javassist-gen.machine-part.opcodes-and-regs-hex)

^{:doc "Here the opcodes of ASM370 are defined by Name and 'HEX code"}
(def BALR 0x05)

(def BCTR 0x06)

(def BCR 0x07)

(def SVC 0x0A)

(def BSM 0x0B)

(def BASSM 0x0C)

(def MVCL 0x0E)

(def CLCL 0x0F)

(def LPR 0x10)

(def LNR 0x11)

(def LTR 0x12)

(def LCR 0x13)

(def NR  0x14)

(def CLR 0x15)

(def OR  0x16)

(def XR  0x17)

(def LR  0x18)

(def CR  0x19)

(def AR  0x1A)
;...............
(def SR	 0x1B)

(def MR	 0x1C)

(def DR  0x1D)

(def ALR	0x1E)

(def SLR	0x1F)

(def STH	0x40)

(def LA	  0x41)

(def STC	0x42)

(def 	IC	0x43)

(def EX	  0x44)

(def BAL	0x45)

(def BCT	0x46)

(def BC	  0x47)

(def LH	  0x48)

(def CH	  0x49)

(def AH	  0x4A)

(def SH	  0x4B)

(def MH	  0x4C)

(def BAS	0x4D)

(def CVD	0x4E)

(def CVB	0x4F)

(def ST	  0x50)

(def N	  0x54)

(def CL	  0x55)

(def O	  0x56)

(def X	  0x57)

(def L	  0x58)

(def C	  0x59)

(def A	  0x5A)

(def S	  0x5B)

(def M	  0x5C)

(def D	  0x5D)

(def AL	   0x5E)

(def SL	   0x5F)

(def BXH	 0x86)
(def BXLE	 0x87)

(def SRL	 0x88)

(def SLL	 0x89)

(def SRA	 0x8A)

(def SLA	 0x8B)

(def SRDL	 0x8C)

(def SLDL	 0x8D)

(def 	SRDA 0x8E)

(def SLDA	 0x8F)

(def STM	 0x90)

(def TM	   0x91)

(def MVI	 0x92)

(def NI	   0x94)

(def CLI	 0x95)

(def OI	   0x96)

(def XI	   0x97)

(def LM	   0x98)

(def CS	   0xBA)

(def CDS	 0xBB)

(def CLM	 0xBD)

(def STCM 0xBE)

(def ICM	0xBF)

(def MVN	0xD1)

(def MVC	0xD2)

(def MVZ	0xD3)

(def NC	  0xD4)

(def 	CLC	0xD5)

(def OC	  0xD6)

(def XC	  0xD7)

(def TR	  0xDC)

(def TRT	0xDD)

(def ED	  0xDE)

(def EDMK	0xDF)

(def MVCIN 0xE8)

(def SRP	0xF0)

(def  MVO	0xF1)

(def PACK	0xF2)

(def UNPK 0xF3)

(def ZAP 0xF8)

(def CP	 0xF9)

(def AP	 0xFA)

(def SP	 0xFB)

(def MP	0xFC)

(def DP	0xFD)

(def mnemonic_to_code
  ^{:doc "Here the table mapping of mnemonics to their op-codes is defined.
  This table is used to translate the cmd's in the source to the op-codes
  of the binary format"}
  {"BALR"  BALR
   "BCTR"  BCTR
   "BCR"   BCR
   "SVC"   SVC
   "BSM"   BSM
   "BASSM" BASSM
   "MVCL"  MVCL
   "CLCL"  CLCL
   "LPR"   LPR
   "LNR"   LNR
   "LTR"   LTR
   "LCR"   LCR
   "NR"    NR
   "CLR"   CLR
   "OR"    OR
   "XR"    XR
   "LR"    LR
   "CR"    CR
   "AR"    AR
   "SR"    SR
   "MR"    MR
   "DR"    DR
   "ALR"   ALR
   "SLR"   SLR
   "STH"   STH
   "LA"    LA
   "STC"   STC
   "IC"    IC
   "EX"    EX
   "BAL"   BAL
   "BCT"   BCT
   "BC"    BC
   "LH"    LH
   "CH"    CH
   "AH"    AH
   "SH"    SH
   "MH"    MH
   "BAS"   BAS
   "CVD"   CVD
   "CVB"   CVB
   "ST"    ST
   "N"     N
   "CL"    CL
   "O"     O
   "X"     X
   "L"     L
   "C"     C
   "A"     A
   "S"     S
   "M"     M
   "D"     D
   "AL"    AL
   "SL"    SL
   "BXH"   BXH
   "BXLE"  BXLE
   "SRL"   SRL
   "SLL"   SLL
   "SRA"   SRA
   "SLA"   SLA
   "SRLD"  SRDL
   "SLDL"  SLDL
   "SRDA"  SRDA
   "SLDA"  SLDA
   "STM"   STM
   "TM"    TM
   "MVI"   MVI
   "NI"    NI
   "CLI"   CLI
   "OI"    OI
   "XI"    XI
   "LM"    LM
   "CS"    CS
   "CDS"   CDS
   "CLM"   CLM
   "STCM"  STCM
   "ICM"   ICM
   "MVN"   MVN
   "MVC"   MVC
   "MVZ"   MVZ
   "NC"    NC
   "CLC"   CLC
   "OC"    OC
   "XC"    XC
   "TR"    TR
   "TRT"   TRT
   "ED"    ED
   "EDMK"  EDMK
   "MVCIN" MVCIN
   "SRP"   SRP
   "MVO"   MVO
   "PACK"  PACK
   "UNPK"  UNPK
   "ZAP"   ZAP
   "CP"    CP
   "AP"    AP
   "SP"    SP
   "MP"    MP
   "DP"    DP})


^{:doc "Here the base registers  of ASM370 are defined by Name and 'HEX code"}
(def R0 0x00)
(def R1 0x01)
(def R2 0x02)
(def R3 0x03)
(def R4 0x04)
(def R5 0x05)
(def R6 0x06)
(def R7 0x07)
(def R8 0x08)
(def R9 0x09)
(def R10 0x0A)
(def R11 0x0B)
(def R12 0x0C)
(def R13 0x0D)
(def R14 0x0E)
(def R15 0x0F)

(def base-regs-to-hex
  ^{:doc "Here the table mapping of reg-names to their reg-codes is defined.
  This table is used to translate the reg-namees in the source to the reg-codes
  of the binary format"}
  {"R0"  R0
   "R1"  R1
   "R2"  R2
   "R3"  R3
   "R4"  R4
   "R5"  R5
   "R6"  R6
   "R7"  R7
   "R8"  R8
   "R9"  R9
   "R10" R10
   "R11" R11
   "R12" R12
   "R13" R13
   "R14" R14
   "R15" R15})

(defn get-code [code-name]
  (get mnemonic_to_code code-name))

(defn get-base-reg [reg-name]
  (get base-regs-to-hex reg-name))

(println (get mnemonic_to_code "A"))
(println (get mnemonic_to_code "ZAP"))