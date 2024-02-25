package jasmin.utils.jas;

import java.io.DataOutputStream;
import java.io.IOException;

public class MethodHandleCP extends CP implements RuntimeConstants {

    private final  short referenceIndex;

    private final  short referenceKind;

    public MethodHandleCP(short referenceKind, short referenceIndex) {
        uniq = referenceKind + "&%$91&" + referenceIndex;
        this.referenceKind = referenceKind;
        this.referenceIndex = referenceIndex;

    }
    @Override
    void resolve(ClassEnv e) {

    }

    @Override
    void write(ClassEnv e, DataOutputStream out) throws IOException, jasError {
        out.writeByte(CONSTANT_METHODHANDLE);
        out.write(referenceKind);
        out.write(referenceIndex);
    }
}
