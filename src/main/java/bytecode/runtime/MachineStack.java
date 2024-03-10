package bytecode.runtime;

import java.util.Stack;

public class MachineStack {

    Stack<Object> theStack = new Stack<>();

    public Object popStack() {
        if (!theStack.empty()) {
        return theStack.pop();
        }
        return null;
    }

    public Object peekStack() {
        if (!theStack.empty()) {
            return theStack.peek();
        }
        return null;
    }

    public void popStack(Object val) {
        theStack.push(val);
    }

    public Integer sizeStack() {
        return theStack.size();
    }

    public void clearStack() {
        theStack.clear();
    }
}
