package ass370.utils;

import clojure.java.api.Clojure;
import clojure.lang.IFn;


public class VMStatusUtil {

    public static byte[] psw_status_word = new byte[8];

    public static synchronized void setPswCCEq() {
        IFn  set_cc_equal = Clojure.var

                ("hgp.genbytec.generator.generators.javassist-gen.machine-part.psw-utils",
                        "set-cc-equal");

        set_cc_equal.invoke(psw_status_word);
    }

    public static synchronized void setPswCCGt() {
        IFn  set_cc_high = Clojure.var

                ("hgp.genbytec.generator.generators.javassist-gen.machine-part.psw-utils",
                        "set-cc-high");

        set_cc_high.invoke(psw_status_word);
    }

    public static synchronized void setPswCCLt() {

        IFn  set_cc_low = Clojure.var

                ("hgp.genbytec.generator.generators.javassist-gen.machine-part.psw-utils",
                        "set-cc-low");

        set_cc_low.invoke(psw_status_word);
    }

    public static synchronized byte getPswCC() {
        IFn  get_cc = Clojure.var

                ("hgp.genbytec.generator.generators.javassist-gen.machine-part.psw-utils",
                        "get-cc");

        Byte cc_val = (Byte) get_cc.invoke(psw_status_word);
        return cc_val;
    }
}
