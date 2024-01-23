package ass370.utils;

import bytecode.addon.AdditionalWrappedOps;
import genbytecj.templates.MainClassTemplate;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Opcode;
import ass370.utils.VM370Regs;
import static ass370.utils.VM370Regs.getAllBaseRegs;
import static bytecinit.InitByteC.getByteCode;
import static bytecinit.InitByteC.getClassPool;

public class VSE370SvcAndMacros {


    private static byte [] bytedefaultAllocWordArea = new byte[10];





    /* Normally it is like this here
    ┌──────┬──────┬──────┬──────┬──────┬──────┬──────┬──────┐
            │ APL─ │ APL─ │ APL─ │ Reser│ APL─ │ APL─ │ Reser│ APL─ │
            │ SID │ NO │ PID │ ved │ SIZE │ PID │ ved │ SIZE │
            └──────┴──────┴──────┴──────┴──────┴──────┴──────┴──────┘
              2 4 6 8 C E 1  13 */
    /*SVC 83 (X'53' - ALLOCATE) */
    public void svc83Allocate(Integer address, Integer size, CtClass type) throws NotFoundException { // this part
        // has to be definitely corrected
        CtClass  ctVMRegs = getClassPool().get(VM370Regs.class.getCanonicalName());
        Integer [] registers = getAllBaseRegs();
        AdditionalWrappedOps.getIConstAsLocal(0, address);
        getByteCode().addNew(type);
        getByteCode().addAstore(0);

        // make a generalized method out of this
        getByteCode().addGetstatic(ctVMRegs, "baseRegs", getAllBaseRegs().
                getClass().getTypeName());
        getByteCode().addIndex(15); // the value has to be changed for real world
        getByteCode().addIload(78);
        getByteCode().addOpcode(Opcode.AALOAD);


    }
}
