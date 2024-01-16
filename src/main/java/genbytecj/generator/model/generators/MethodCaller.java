package genbytecj.generator.model.generators;


import genbytecj.generator.model.types.base.MetaType;
import genbytecj.generator.model.utils.ClazzFileContainer;
import genbytecj.generator.model.utils.ParamWrapper;

import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import static genbytecj.generator.model.utils.StatementDSL.Casts.cast;
import static genbytecj.generator.model.utils.StatementDSL.Statement;
import static genbytecj.generator.model.utils.StatementDSL.call;

abstract class MethodCaller extends Generator {

    public MethodCaller(Random rand, ClazzFileContainer clazzContainer) {
        super(rand, clazzContainer);
    }

    // TODO unite paramValues and paramTypes (e.g. require expression / typecast)
    static String generateMethodCallString(String methodName, MetaType<?>[] paramTypes, ParamWrapper[] paramValues) {
        String params = Optional.ofNullable(paramValues)
                .map(v -> IntStream.range(0, paramTypes.length)
                        .mapToObj(i ->
                                cast(paramValues[i].getParamValue())
                                        .to(paramTypes[i].descriptor()))
                        .collect(Collectors.joining(", ")))
                .orElse("");
        return Statement(call(methodName, params));
    }

}
