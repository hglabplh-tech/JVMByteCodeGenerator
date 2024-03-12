package bytecode.runtime;

import clojure.lang.Symbol;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Optional;

public class ByteCodeDump {
    private static final Deque<Frame> dumpStack = new ArrayDeque<Frame>();

    public static synchronized void pushFrame(Frame theFrame) {
        dumpStack.push(theFrame);
    }

    public static synchronized Frame popFrame() {
        return dumpStack.pop();
    }

    public static synchronized Frame peekFrame() {
        return dumpStack.peek();
    }

    public static synchronized Frame peekFirstFrame() {
        return dumpStack.peekFirst();
    }

    public static synchronized Frame peekLastFrame() {
        return dumpStack.peekLast();
    }

    public static synchronized Frame getLastFrame() {
        return dumpStack.getLast();
    }

    public static synchronized Frame getFirstFrame() {
        return dumpStack.getFirst();
    }

    public static synchronized Optional<ByteCodeEnv.Binding> lookup(String ref) {
        Iterator<Frame> dumpIt = dumpStack.iterator();
        Optional<ByteCodeEnv.Binding> found = Optional.empty();

        while (dumpIt.hasNext()) {
            Frame actFrame = dumpIt.next();
            ByteCodeEnv actEnv = actFrame.thisEnv();
            found = actEnv.lookupBinding(ref);
            if (found.isPresent()) {
                return found;
            }
        }
        return found;

    }
}
