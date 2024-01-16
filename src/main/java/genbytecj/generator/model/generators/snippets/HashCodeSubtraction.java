package genbytecj.generator.model.generators.snippets;


import genbytecj.generator.model.logger.MethodLogger;
import genbytecj.generator.model.utils.ClazzFileContainer;
import genbytecj.generator.model.utils.RandomSupplier;

import static genbytecj.generator.model.utils.StatementDSL.*;



public class HashCodeSubtraction implements Snippet {
    @Override
    public <T> boolean isPossible(MethodLogger<T> method) {
        return !method.isStatic();
    }

    @Override
    public String generate(RandomSupplier __, ClazzFileContainer ___, MethodLogger ____) {
        return Statement(
                SystemOutPrintln(
                        concat(
                                asStr("hash difference = "),
                                inPar(subtract(
                                        call("hashCode"),
                                        call("hashCode")
                                ))
                        )
                )
        );
    }
}
