package bytecode.runtime;


import jasmin.utils.jas.*;

public class TypeUtils {

    public static CP makeTypedCP (ClassEnv classEnv, String type, Object value) {
        CP resultCP = switch (type) {
            case ":integer" -> new IntegerCP((Integer) value);
            case ":long" -> new LongCP((Long) value);
            case ":float" -> new FloatCP((Float) value);
            case ":double" -> new DoubleCP((Double) value);
            case ":utf8" -> new AsciiCP((String) value);
            case ":string" -> new StringCP((String) value); //TODO: look what we do with free defined objects
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
        classEnv.addCPItem(resultCP);
        return resultCP;
    }

}
