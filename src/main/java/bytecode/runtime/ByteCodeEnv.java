package bytecode.runtime;

import clojure.lang.Symbol;

import java.util.*;

public class ByteCodeEnv {

    private  Map<String, Binding> environment = new LinkedHashMap<>();

    public ByteCodeEnv() {

    }

    public synchronized Map<String, Binding> environment() {
        return Collections.unmodifiableMap( environment);
    }

    public  synchronized void addBinding(String ref, Object val, String typeId) {
        Binding newBinding = new Binding(ref, val, typeId);
        environment.put(ref, newBinding);
    }

    public  synchronized  Optional<Binding> lookupBinding(String ref) {
        Binding reference = environment.get(ref);
        if (reference != null) {
            return Optional.of(reference);
        }
        return Optional.empty();
    }

    public synchronized  void removeBinding(String ref) {
        environment.remove(ref);
    }



    public static class Binding {
        private final String varRef;

        private final Object value;

        private final String typeId;

        public Binding(String varRef, Object value, String typeId) {
            this.varRef = varRef;
            this.value = value;
            this.typeId = typeId;
        }

        public String varRef() {
            return varRef;
        }

        public Object value() {
            return value;
        }

        public String typeId() {
            return typeId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Binding binding = (Binding) o;
            return Objects.equals(varRef, binding.varRef) && Objects.equals(value, binding.value) && Objects.equals(typeId, binding.typeId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(varRef, value, typeId);
        }

        @Override
        public String toString() {
            return "Binding{" +
                    "varRef='" + varRef + '\'' +
                    ", value=" + value +
                    ", typeId=" + typeId +
                    '}';
        }
    }
}
