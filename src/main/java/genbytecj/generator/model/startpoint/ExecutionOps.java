package genbytecj.generator.model.startpoint;

import java.util.HashMap;
import java.util.Map;

public class ExecutionOps {

    /**
     * Here the options map takes place
     */
    private final Map<LibOptions, OptValue<?>> optsMap;

    public ExecutionOps() {
        optsMap = defineDefaults();
    }

    public Map<LibOptions, OptValue<?>> getSettings() {
        return this.optsMap;
    }


    private static Map<LibOptions, OptValue<?>> defineDefaults() {
        Map<LibOptions, OptValue<?>> defaultMap = new HashMap<>();
        defaultMap.put(LibOptions.L, new OptValue<Integer>(100));
        defaultMap.put(LibOptions.F, new OptValue<Boolean>(Boolean.TRUE));
        defaultMap.put(LibOptions.LV, new OptValue<Boolean>(Boolean.TRUE));
        defaultMap.put(LibOptions.GA, new OptValue<Boolean>(Boolean.TRUE));
        defaultMap.put(LibOptions.LA, new OptValue<Boolean>(Boolean.TRUE));
        defaultMap.put(LibOptions.M, new OptValue<Boolean>(Boolean.TRUE));
        defaultMap.put(LibOptions.MC, new OptValue<Boolean>(Boolean.TRUE));
        defaultMap.put(LibOptions.ML, new OptValue<Integer>(10));
        defaultMap.put(LibOptions.MP, new OptValue<Integer>(7));
        defaultMap.put(LibOptions.MO, new OptValue<Boolean>(Boolean.TRUE));

        return defaultMap;
    }

    public static class OptValue<T> {
        private final T value;

        OptValue(T value) {
            this.value = value;
        }

        public Integer toInteger() {
            return (Integer)this.value;
        }

        public Boolean toBoolean() {
            return (Boolean) this.value;
        }
    }
}
