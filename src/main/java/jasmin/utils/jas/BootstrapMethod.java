package jasmin.utils.jas;

public class BootstrapMethod {
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
