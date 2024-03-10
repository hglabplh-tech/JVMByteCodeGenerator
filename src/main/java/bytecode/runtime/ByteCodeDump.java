package bytecode.runtime;

import clojure.lang.Symbol;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Optional;

public class ByteCodeDump {
    private static Deque<Frame> dumpStack = new ArrayDeque();

    public static void   pushFrame(Frame theFrame) {
        dumpStack.push(theFrame);
    }

    public static Frame   popFrame() {
        return dumpStack.pop();
    }

    public static synchronized Optional<ByteCodeEnv.Binding> lookup(String ref) {
        Iterator<Frame> dumpIt= dumpStack.iterator();
        Optional<ByteCodeEnv.Binding> found = Optional.empty();

        while(dumpIt.hasNext()) {
            Frame actFrame = dumpIt.next();
            ByteCodeEnv actEnv = actFrame.thisEnv();
            found =  actEnv.lookupBinding(ref);
            if (found.isPresent()) {
                return found;
            }
        }
        return found;

    }



}
