package bytecode.addon;

import javassist.CtClass;
import javassist.bytecode.Opcode;

import java.util.Iterator;
import java.util.List;

import static bytecinit.InitByteC.*;

public class MethodsInvoker {

    public static  void pushParameters(List<Object> parmValues, CtClass... parameterTypes) {
        Iterator<Object> listIter = parmValues.iterator();
        for (CtClass type : parameterTypes) {
            Object parmValObj = null;
            if (listIter.hasNext()) {
                parmValObj = listIter.next();
            }
            if (type.equals(CtClass.intType)) {
                getByteCode().addIconst(((Integer) parmValObj));
            } else if (type.equals(CtClass.booleanType)) {
                getByteCode().addIconst(((Integer) parmValObj));
            } else if (type.equals(CtClass.byteType)) {
                getByteCode().addIconst(((Byte) parmValObj));
            } else if (type.equals(CtClass.charType)) {
                getByteCode().addIconst(((Integer) parmValObj));
            } else if (type.equals(CtClass.doubleType)) {
                getByteCode().addDconst(((Double) parmValObj).intValue());
            } else if (type.equals(CtClass.floatType)) {
                getByteCode().addFconst(((Double) parmValObj).intValue());
            } else if (type.equals(CtClass.longType)) {
                getByteCode().addLconst(((Long) parmValObj));
            } else if (type.equals(CtClass.shortType)) {
                getByteCode().addIconst((Short) parmValObj);
            } else
                getByteCode().addNew(type);

            }


        }


    public static void popReturn(CtClass type, Integer fieldIndex) {
            if (type.equals(CtClass.intType)) {
                byteCode.addIstore(fieldIndex);
            } else if (type.equals(CtClass.booleanType)) {
                byteCode.addIstore(fieldIndex);
            } else if (type.equals(CtClass.byteType)) {
                byteCode.addIstore(fieldIndex);
            } else if (type.equals(CtClass.charType)) {
                byteCode.addIstore(fieldIndex);
            } else if (type.equals(CtClass.doubleType)) {
                getByteCode().addDconst(fieldIndex);
            } else if (type.equals(CtClass.floatType)) {
                getByteCode().addFconst(fieldIndex);
            } else if (type.equals(CtClass.longType)) {
                getByteCode().addLstore(fieldIndex);
            } else if (type.equals(CtClass.shortType)) {
                getByteCode().addIstore(fieldIndex);
            } else {
                CtClass internType = getClassPool().makeClass(type.getPackageName()); // reverence type
                getByteCode().addAstore(fieldIndex);
            }

    }

    public static void funInvoke(CtClass target, String name,
                          InvocationTypes callType, CtClass returnType,
                          List<Object> parmValues,
                          CtClass... parameterTypes) {
        pushParameters(parmValues, parameterTypes);
        switch (callType) {
            case STATIC -> getByteCode()
                    .addInvokestatic(target, name, returnType, parameterTypes);
            case SPECIAL -> getByteCode()
                    .addInvokespecial(target, name, returnType, parameterTypes);
            case VIRTUELL -> getByteCode()
                    .addInvokevirtual(target, name, returnType, parameterTypes);
            case INTERFACE -> getByteCode()
                    .addInvokeinterface(target, name,
                            returnType, parameterTypes, 1);
        }
        //popReturn(returnType, 1);

    }

    public enum InvocationTypes {
        STATIC(1, "static"),
        SPECIAL(2, "special"),
        VIRTUELL(3, "virtuell"),
        INTERFACE(4, "interface")
        ;

        final Integer invoceCode;

        final String invokeName;
        InvocationTypes(Integer code, String name) {
            this.invoceCode = code;
            this.invokeName = name;
        }

        public Integer getInvoceCode() {
            return invoceCode;
        }

        public String getInvokeName() {
            return invokeName;
        }
    }
}
