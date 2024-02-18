package ass370.utils;

import clojure.java.api.Clojure;
import clojure.lang.IFn;




import static bytecinit.InitByteC.getByteCode;


public class VMStatusUtil {


    public static byte[] psw_status_word = new byte[8];

    public static synchronized void setPswCCEq() {
        IFn set_cc_equal = Clojure.var

                ("hgp.genbytec.generator.generators.javassist-gen.machine-part.psw-utils",
                        "set-cc-equal");

        set_cc_equal.invoke(psw_status_word);
        Integer cc_state = getPswCC();
        loadStack(cc_state);
    }

    public static synchronized void setPswCCGt() {
        IFn set_cc_high = Clojure.var

                ("hgp.genbytec.generator.generators.javassist-gen.machine-part.psw-utils",
                        "set-cc-high");

        set_cc_high.invoke(psw_status_word);
        Integer cc_state = getPswCC();
        loadStack(cc_state);
    }

    public static synchronized void setPswCCLt() {

        IFn set_cc_low = Clojure.var

                ("hgp.genbytec.generator.generators.javassist-gen.machine-part.psw-utils",
                        "set-cc-low");

        set_cc_low.invoke(psw_status_word);
        Integer cc_state = getPswCC();
        loadStack(cc_state);
    }

    private static final void loadStack(Integer cc_state) {
        switch (cc_state) {
            case 0x00:
                getByteCode().addIconst(0);
                break;
            case 0x04:
                getByteCode().addIconst(-1);
                break;
            case 0x08:
                getByteCode().addIconst(1);
                break;
            case 0x0C:
                getByteCode().addIconst(12);
                break;
        }

    }

    public static synchronized Integer getPswCC() {
        IFn get_cc = Clojure.var

                ("hgp.genbytec.generator.generators.javassist-gen.machine-part.psw-utils",
                        "get-cc");

        Integer cc_val = (Integer) get_cc.invoke(psw_status_word);
        return cc_val;
    }
}
