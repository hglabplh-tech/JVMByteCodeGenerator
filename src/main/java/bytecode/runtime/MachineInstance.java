package bytecode.runtime;

public class MachineInstance {

    private static MachineInstance instance = null;

    private Frame actualFrame = null;

    private MachineInstance() {
        this.actualFrame = new Frame(new ByteCodeEnv(), new MachineStack());
    }

    public synchronized void changeActualFrame() {
        this.actualFrame = ByteCodeDump.popFrame();
    }

    public synchronized void freshActualFrame() {
        ByteCodeDump.pushFrame(actualFrame());
        this.actualFrame = new Frame(new ByteCodeEnv(), new MachineStack());
    }

    public synchronized Frame actualFrame() {
        return actualFrame;
    }

    public static MachineInstance singleInst() {
        if (instance == null) {
            instance = new MachineInstance();
        }
        return instance;
    }
}
