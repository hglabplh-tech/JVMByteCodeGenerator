package bytecode.addon;

import javassist.*;
import javassist.bytecode.Bytecode;
import javassist.bytecode.Descriptor;
import javassist.bytecode.Opcode;

import java.util.Arrays;
import java.util.List;

import static bytecinit.InitByteC.*;

public class ClassFieldMethodHandling {


    public static void createField(CtClass destClass, String name,
                                   CtClass type) throws CannotCompileException {
        CtField newField = new CtField(type, name, destClass);
        destClass.addField(newField);
    }

    public static void createFieldInit(CtClass destClass, String name,
                                   CtClass type, String initValue) throws CannotCompileException {
        CtField newField = new CtField(type, name, destClass);
        destClass.addField(newField, initValue);
    }

    public static void putFieldValue(CtClass destClass, String name,
                                     CtClass type, Integer value) throws NotFoundException {

        getByteCode().addOpcode(Opcode.ALOAD);
        getByteCode().addIndex(getFieldNo(destClass, name));
        if (type.equals(CtClass.intType)) {
            getByteCode().addIload(value);
        }
        getByteCode().addPutfield(destClass, name, Descriptor.of(type));
    }

    public static void putFieldValueOp(CtClass destClass, String name,
                                     CtClass type) throws NotFoundException {
        getByteCode().addPutfield(destClass, name, Descriptor.of(type));
    }

    public static void getFieldValue(CtClass sourceClass, String name, CtClass type) {
        // put the objectref to stack
        getByteCode().addOpcode(Opcode.ALOAD);
        getByteCode().addIndex(getFieldNo(sourceClass, name));
        getByteCode().addGetfield(sourceClass, name, Descriptor.of(type));
        // get the value from stack
    }

    public static void createConstructor(CtClass theClass,  CtClass[] parameters) throws CannotCompileException {
        CtConstructor newConstructor = new CtConstructor(parameters, theClass);
        theClass.addConstructor(newConstructor);
    }

    public static void createMethod(CtClass theClass, CtClass type, String name, CtClass[] parameters) throws CannotCompileException {
        CtMethod newMethod = new CtMethod(type, name, parameters, theClass);
        theClass.addMethod(newMethod);
    }

    public static void reallyInvokeStatic(CtClass targetClass, String name,
                                          CtClass retType, CtClass [] parameters) {
        getByteCode().addInvokestatic(targetClass, name, retType, parameters);
    }


    private static Integer getFieldNo(CtClass destClass, String name) {


        CtField[] theFields = destClass.getFields();
        int index = 0;
        for (index = 0; index < theFields.length; index++) {
            CtField field = theFields[index];
            if (field.getName().equals(name))
                break;
        }

        getByteCode().addOpcode(Opcode.ALOAD);
        getByteCode().addIndex(index);
        return index;
    }


    public static void createNewRefArray(Integer typeInt) throws NotFoundException,
            CannotCompileException {
        createMethod(VarAssignNewAndMore.storClass,
                CtClass.voidType, "createStorArray", new CtClass[]{CtClass.intType});
        CtClass primType = VarAssignNewAndMore.getReferenceType(typeInt);
        Integer arrTypeInt = ((CtPrimitiveType)primType).getArrayType();
        CtClass type = VarAssignNewAndMore.getReferenceType(arrTypeInt);
        getByteCode().addMultiNewarray(type, 255);
        String arrayFieldName = genArrayFieldName(typeInt);
        String arrayIndexFieldName = genArrayIndexFieldName(typeInt);
        createField(VarAssignNewAndMore.storClass,arrayFieldName, type);
        createFieldInit(VarAssignNewAndMore.storClass,
                arrayIndexFieldName,
                CtClass.intType, "-1");

    }

    private static String genArrayFieldName (Integer typeInt) {
        return "Array" + typeInt.toString();
    }

    static String genArrayIndexFieldName(Integer typeInt) {
        return "Array" + typeInt.toString() + "_Int";
    }
}
