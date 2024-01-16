package genbytecj.generator.model.generators.snippets;

import genbytecj.generator.model.logger.MethodLogger;
import genbytecj.generator.model.types.base.MetaType;
import genbytecj.generator.model.utils.ClazzFileContainer;
import genbytecj.generator.model.utils.ErrorUtils;
import genbytecj.generator.model.utils.RandomSupplier;

import static genbytecj.generator.model.utils.StatementDSL.*;



/**
 * Generates and prints a check for {@code A.class.isAssignableFrom(B.class)}
 * where {@code A} and {@code B} represent static types.
 */
public class AssignableFromCall implements Snippet {

    @Override
    public <T> boolean isPossible(MethodLogger<T> method) {
        // can be placed anywhere since the types are static
        return true;
    }

    @Override
    public String generate(RandomSupplier randomSupplier, ClazzFileContainer __, MethodLogger ___) {
        MetaType<?> t1 = randomSupplier.type();
        MetaType<?> t2 = randomSupplier.types()
                .filter(t -> !t.equals(t1))
                .findFirst()
                .orElseThrow(ErrorUtils::shouldNotReachHere);
        String c1 = t1.descriptor();
        String c2 = t2.descriptor();

        return Statement(
                SystemOutPrintln(
                        concat(
                                asStr(
                                        String.format(
                                                "%s is assignable from %s: ",
                                                c1,
                                                c2)
                                ),
                                String.format(
                                        "%s.class.isAssignableFrom(%s.class)",
                                        c1,
                                        c2
                                )
                        )
                )
        );
    }
}
