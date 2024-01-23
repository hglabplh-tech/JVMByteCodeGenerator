package bytecinit;

import javassist.ClassPool;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ConstPool;

public class InitByteC {

    private static final ClassPool classPool = ClassPool.getDefault();

    private static final ConstPool constPool = new ConstPool("genbytecj.templates.MainClassTemplate");

    private static final Bytecode byteCode = new Bytecode(constPool, 0, 0);

    public static ClassPool getClassPool() {
        return classPool;
    }

    public static ConstPool getConstPool() {
        return constPool;
    }

    public static Bytecode getByteCode() {
        return byteCode;
    }

}
