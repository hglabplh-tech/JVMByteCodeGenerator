package bytecode.addon;

import javassist.*;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Opcode;

import static bytecinit.InitByteC.*;

public class VarAssignNewAndMore {


    public static CtClass storClass;


    static {
        storClass = createClassForStorage();
        try {
            ClassFieldMethodHandling.createNewRefArray(ConstPool.CONST_Integer);
            ClassFieldMethodHandling.createNewRefArray(ConstPool.CONST_Float);
            ClassFieldMethodHandling.createNewRefArray(ConstPool.CONST_Long);
            ClassFieldMethodHandling.createNewRefArray(ConstPool.CONST_Double);
            ClassFieldMethodHandling.createNewRefArray(ConstPool.CONST_String);
            ClassFieldMethodHandling.createNewRefArray(ConstPool.CONST_Utf8);
            createIncreaseCounterMeth();

        } catch (Exception e) {
            System.exit(-1);
        }
    }
    public static CtClass getReferenceType(Integer typeIndex) throws NotFoundException {
        String strClassInfo = getConstPool().getClassInfo(typeIndex);
        return getClassPool().getCtClass(strClassInfo);
    }


    public static void newEachType(Integer typeInt) throws NotFoundException {
        CtClass type = getReferenceType(typeInt);
        getByteCode().addNew(type.getName());

    }

    public static void addToTypeArray(Integer typeInt) throws NotFoundException {
        getByteCode().addOpcode(Opcode.ASTORE_0);
        Integer store = Opcode.NOP;
        Integer load = Opcode.NOP;
        switch (typeInt) {
            case ConstPool.CONST_Double:
                store = Opcode.DASTORE;

                break;
            case ConstPool.CONST_Float:
                store = Opcode.FASTORE;
                break;
            case ConstPool.CONST_Integer:
                store = Opcode.IASTORE;

                break;
            case ConstPool.CONST_Long:
                store = Opcode.LASTORE;

                break;
            default:
                break;
        }
        // check this here
        getByteCode().addOpcode(Opcode.ALOAD_1);
        getByteCode().addOpcode(Opcode.ILOAD_1);
        getByteCode().addOpcode(store);


    }

    private static void createIncreaseCounterMeth() throws CannotCompileException, NotFoundException {
        ClassFieldMethodHandling.createMethod(storClass,
                CtClass.intType, "incIndex", null);
        ClassFieldMethodHandling.getFieldValue(getReferenceType(ConstPool.CONST_Integer),
                ClassFieldMethodHandling.genArrayIndexFieldName(ConstPool.CONST_Integer), CtClass.intType);
        getByteCode().addOpcode(Opcode.ISTORE_0);
        getByteCode().add(1);
        getByteCode().addOpcode(Opcode.IINC);
        ClassFieldMethodHandling.putFieldValueOp(getReferenceType(ConstPool.CONST_Integer),
                ClassFieldMethodHandling.genArrayIndexFieldName(ConstPool.CONST_Integer), CtClass.intType);
        getByteCode().addRet(0);
    }

    public static CtClass createClassForStorage() {
        return getClassPool().makeClass("InternStorage");
    }
}
