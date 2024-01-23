package bytecode.addon;

import genbytecj.templates.MainClassTemplate;
import javassist.bytecode.Opcode;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Bytecode;

import static bytecinit.InitByteC.getByteCode;
import static bytecinit.InitByteC.getConstPool;

public class AdditionalWrappedOps {

    private static final ConstPool constPoolInst = getConstPool();


    private static final Bytecode byteCodeInst = getByteCode();


    public static void refFromLocalToArray(int localIndex, int localArrIndex, int arrayIndex) {
        byteCodeInst.addOpcode(Opcode.ALOAD);
        byteCodeInst.add(localIndex);
        byteCodeInst.addAload(localArrIndex);
        byteCodeInst.add(arrayIndex);
        byteCodeInst.addIload(localIndex);
        byteCodeInst.addOpcode(Opcode.AASTORE);

    }

    public static void refFromArrayToLocal(int localIndex, int localArrIndex, int arrayIndex) {
        byteCodeInst.addAload(localArrIndex);
        byteCodeInst.add(arrayIndex);
        byteCodeInst.addOpcode(Opcode.AALOAD);
    }

    public static void intFromLocaToArray(int localIndex, int localArrIndex, int arrayIndex) {
        byteCodeInst.addAload(localArrIndex);
        byteCodeInst.addIload(arrayIndex);
        byteCodeInst.addIload(localIndex);
        byteCodeInst.addOpcode(Opcode.IALOAD);
    }

    public static void getIConstAsLocal(Integer index, Integer value) {
        byteCodeInst.addIconst(value);
        byteCodeInst.addIstore(index);
    }
}
