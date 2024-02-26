package jasmin.utils.jas;

import java.io.DataOutputStream;
import java.io.IOException;

/*BootstrapMethods_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 num_bootstrap_methods;
    {   u2 bootstrap_method_ref;
        u2 num_bootstrap_arguments;
        u2 bootstrap_arguments[num_bootstrap_arguments];
    } bootstrap_methods[num_bootstrap_methods];
}
*/
public class BootstrapMethsAttr {

    short attributeNameIndex;
    short attributeLength;

    short numBootstrapMeths;

    BootstrapMethod[] bootstrapMeths;
    static final CP attr = new AsciiCP("BootstrapMethods");

    public BootstrapMethsAttr(short numBootstrapMeths) {
        this.numBootstrapMeths = numBootstrapMeths;
        this.bootstrapMeths = new BootstrapMethod[numBootstrapMeths];
    }

    public BootstrapMethsAttr(int numBootstrapMeths, BootstrapMethod[] bootstrapMeths) {
        this.numBootstrapMeths = (short) numBootstrapMeths;
        this.bootstrapMeths = bootstrapMeths;
    }

    public short attributeNameIndex() {
        return attributeNameIndex;
    }

    void resolve(ClassEnv e) {
        e.addCPItem(attr);
        try {
            attributeNameIndex = e.getCPIndex(attr);
        } catch (jasError ex) {
            throw new RuntimeException(ex);
        }

    }

    void write(ClassEnv e, DataOutputStream out)
            throws IOException, jasError {
        out.writeShort(e.getCPIndex(attr));
        //out.writeInt(ann.size());
        //ann.write(e, out);
    }

    public static BootstrapMethod createBootstrapMethod(short bootstrapMethodRef, short numBootstrapArguments) {
        return new BootstrapMethod(bootstrapMethodRef, numBootstrapArguments);
    }


    public static class BootstrapMethod {
        short bootstrapMethodRef;
        short numBootstrapArguments;
        short[] bootstrap_arguments;

        public BootstrapMethod(short bootstrapMethodRef, short numBootstrapArguments) {
            this(bootstrapMethodRef,
            numBootstrapArguments,
            new short[numBootstrapArguments]);
        }

        public BootstrapMethod(short bootstrapMethodRef, short numBootstrapArguments, short[] bootstrap_arguments) {
            this.bootstrapMethodRef = bootstrapMethodRef;
            this.numBootstrapArguments = numBootstrapArguments;
            this.bootstrap_arguments = bootstrap_arguments;
        }
    }


}
