package jasmin.utils.jas;

import java.io.DataOutputStream;
import java.io.IOException;

public class InvokeDynamicCP extends CP implements RuntimeConstants {

    private final  short bootstrap;
    NameTypeCP nt;
    public InvokeDynamicCP(short bootstrap, String varname, String sig) {
        uniq = bootstrap + "vokeDyn::&%$91&" + varname + "*(012$" + sig;
        this.bootstrap = bootstrap;
        this.nt = new NameTypeCP(varname, sig);

    }
    @Override
    void resolve(ClassEnv e) {
        e.addCPItem(nt);
    }

    @Override
    void write(ClassEnv e, DataOutputStream out) throws IOException, jasError {
        out.writeByte(CONSTANT_INVOKEDYNAMIC);
        out.write(bootstrap);
        out.write(e.getCPIndex(nt));
    }
}
