(ns hgp.genbytec.generator.generators.javassist-gen.machine-part.psw-utils)

^{:doc "Here the utilities for setting the program status word are defined
  PWD description: Bit | 1 | R | PER Mask
                   Bit | 5 | T | DAT mode
                   Bit | 6 | IO| I/O Mask; subject to channel mask in CR2
                   Bit | 7 | EX | External Mask; subject to external subclass mask in CR0
                   Bits|8-11| key | PSW key
                   Bit | 12 | E=1| Extended Control Mode
                   Bit | 13 | M | Machine-check mask
                   Bit | 14 | W | Wait state
                   Bit | 15 | P | Problem state
                   Bit | 16 | S | Address-Space Control /
                                | 0=primary-space mode /
                                | 1=Secondary-space mode
                   Bit |18/19| CC | Condition Code
                   Bit |20-23| PM | Program Mask
                                  | Bit 20 -> Fixed-point overflow
                                  | Bit 21 -> Decimal overflow
                                  | Bit 22 -> Exponent underflow
                                  | Bit 23 -> Significance
                   Bit | 32 | A | Addressing Mode 0=24 bit ; 1=31 bit
                   Bits | 33-63 | IA | Instruction address"}
(def addr-mode-ec 0x01)
(def addr-mode-virt 0x00)
(defn get-cc[value]
  (bit-and (aget value 2)  2r00001100) )
(defn set-cc-equal [value]
  (let [cc-byte (bit-clear (aget value 2)  2)
        cc-byte (bit-clear cc-byte  3)]
    (aset-byte value 2 cc-byte)
    value
    ))

(defn set-cc-overflow [value]
  (let [cc-byte (bit-set (aget value 2)  2)
        cc-byte (bit-set cc-byte  3)]
    (aset-byte value 2 cc-byte)
    value
    ))
(defn set-cc-low [value]
  (let [cc-byte (bit-set (aget value 2)  2)]
    (aset-byte value 2 cc-byte)
    value
                  ))

(defn clear-cc-low [value]
  (let [cc-byte (bit-clear (aget value 2)  2)]
    (aset-byte value 2 cc-byte)
    value
    ))
(defn set-cc-high [value]
  (let [cc-byte (bit-set (aget value 2)  3)]
    (aset-byte value 2 cc-byte)
    value
    ))
(defn clear-cc-high [value]
  (let [cc-byte (bit-clear (aget value 2)  3)]
    (aset-byte value 2 cc-byte)
    value
    ))

(defn set-address-mode [psw mode]
  (let [addr-byte (bit-or (aget psw 4) mode)  ]
    (aset-byte psw 4 addr-byte)
    psw
    ))
(defn init-psw [proc-psw]
  (set-cc-equal proc-psw)
  (set-address-mode proc-psw  addr-mode-ec)
  proc-psw)





