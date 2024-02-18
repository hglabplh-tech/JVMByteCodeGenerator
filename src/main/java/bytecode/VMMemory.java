package bytecode;


import javassist.CtClass;
import javassist.NotFoundException;

import javassist.bytecode.Opcode;


import java.util.Arrays;

import static bytecinit.InitByteC.getByteCode;
import static bytecinit.InitByteC.getClassPool;


public class VMMemory {

    private final MEMDataSource memSource;
    VMMemory(Integer storSize) {
        this.memSource = new MEMDataSource(storSize);
    }

    public MEMDataSource memSource() {
        return memSource;
    }

    public static class MEMDataSource {

        private final Byte[] storage;
        public MEMDataSource(Integer storSize) {
            storage = new Byte[storSize];
            Arrays.fill(this.storage, Byte.valueOf("0"));
        }

        public void addCallSystemArrayCopy(Object srcArr,Integer sourceAddress,
                                             Object targetArr,
                                             Integer targetAddress,
                                             Integer  size) throws NotFoundException {
            CtClass system = getClassPool().get(System.class.getCanonicalName());
            CtClass object = getClassPool().get(Object.class.getCanonicalName());
            CtClass[] parameters = {object, CtClass.intType, object,  CtClass.intType,  CtClass.intType};
            getByteCode().addOpcode(Opcode.ALOAD_1);
            getByteCode().addIconst(sourceAddress);
            getByteCode().addOpcode(Opcode.ALOAD_2);
            getByteCode().addIconst(targetAddress);
            getByteCode().addIconst(size);

            getByteCode().addInvokestatic(system, "arraycopy", CtClass.voidType, parameters);
        }

    }
}
