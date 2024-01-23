package ass370.utils;

import java.util.Arrays;

public class VM370Regs {
    public static Integer [] baseRegs = new Integer [16];


    static {
        Arrays.fill(new Integer[0], 0);
    }



    public static Integer getReg(Integer regNo) {
        return baseRegs[regNo];
    }

    public static Integer [] getAllBaseRegs() {
        return baseRegs;
    }

}
