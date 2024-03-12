package bytecode.runtime;

public class MachineAndAdminUtil {

    private static ByteCodeEnv actEnvInstance = null;
    /**
     * initialize the runtime base
     */
    static {
        machineInstance();
        actEnvInstance = newEnvironment();
    }

    public static MachineInstance machineInstance() {
        return MachineInstance.singleInst();
    }

    public static ByteCodeEnv actualEnv() {
        return actEnvInstance;
    }

    public static ByteCodeEnv newEnvironment() {
        return new ByteCodeEnv();
    }

    public static synchronized ByteCodeEnv getEnvFromLastFrame() {
        return machineInstance().actualFrame().thisEnv();
    }

    public static synchronized MachineStack getStackFromLastFrame() {
        return machineInstance().actualFrame().thisStack();
    }


}
