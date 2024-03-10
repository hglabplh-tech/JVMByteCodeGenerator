package bytecode.runtime;

import java.util.Objects;

public class Frame {

    private final ByteCodeEnv thisEnv;

    private final MachineStack thisStack;

    public Frame(ByteCodeEnv thisEnv, MachineStack thisStack) {
        this.thisEnv = thisEnv;
        this.thisStack = thisStack;
    }

    public ByteCodeEnv thisEnv() {
        return thisEnv;
    }

    public MachineStack thisStack() {
        return thisStack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(thisEnv, frame.thisEnv) && Objects.equals(thisStack, frame.thisStack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(thisEnv, thisStack);
    }
}
