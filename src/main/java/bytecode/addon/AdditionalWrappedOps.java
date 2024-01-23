package bytecode.addon;

import ass370.utils.VM370Regs;
import genbytecj.templates.MainClassTemplate;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.Opcode;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Bytecode;

import static ass370.utils.VM370Regs.getAllBaseRegs;
import static bytecinit.InitByteC.*;

public class AdditionalWrappedOps {

    private static final ConstPool constPoolInst = getConstPool();

    private static final ClassPool classPoolInst = getClassPool();


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

    public static CtClass getRegisterClass() throws NotFoundException {
        CtClass ctVMRegs = getClassPool().get(VM370Regs.class.getCanonicalName());

        return ctVMRegs;
    }

    public static void getRegisterArray() throws NotFoundException {
        CtClass regHolder = getRegisterClass();
        getByteCode().addGetstatic(regHolder, "baseRegs", getAllBaseRegs().
                getClass().getTypeName());
    }

    public static void storeRegs(Integer register) {
        try {
            getByteCode().addIload(register);
            getRegisterArray();
            getByteCode().addOpcode(Opcode.IASTORE);
        } catch (NotFoundException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public static void loadRegs(Integer register)  {
        try {
            getRegisterArray();
            getByteCode().addIload(register);
            getByteCode().addOpcode(Opcode.IALOAD);
        } catch (NotFoundException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
