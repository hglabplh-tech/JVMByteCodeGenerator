package genbytecj.generator.model.generators;



import genbytecj.generator.model.logger.ClazzLogger;
import genbytecj.generator.model.logger.FieldVarLogger;
import genbytecj.generator.model.logger.MethodLogger;
import genbytecj.generator.model.types.base.MetaType;
import genbytecj.generator.model.utils.Randomizer;

import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static genbytecj.generator.model.utils.StatementDSL.Assignments.assign;
import static genbytecj.generator.model.utils.StatementDSL.Casts.cast;
import static genbytecj.generator.model.utils.StatementDSL.Statement;


public class TypeCastGenerator extends Generator {

    private final Randomizer randomizer;

    public TypeCastGenerator(Random rand, RandomCodeGenerator clazzContainer) {
        super(rand, clazzContainer.getClazzFileContainer());

        this.randomizer = new Randomizer(rand);
    }

    public void generatePrimitiveTypeCast(MethodLogger<?> method) {
        ClazzLogger cl = getClazzLogger();

        Predicate<FieldVarLogger<?>> isPrimitiveCastable = v ->
                v.getType().kind() != MetaType.Kind.BOOLEAN &&
                        v.getType().kind() != MetaType.Kind.INSTANCE &&
                        v.getType().kind() != MetaType.Kind.VOID &&
                        v.getType().kind() != MetaType.Kind.ARRAY;

        randomizer.shuffle(
                cl.getNonFinalVarsUsableInMethod(method)
                        .filter(isPrimitiveCastable)
                        .flatMap(dest ->
                                cl.getInitializedVarsUsableInMethod(method)
                                        .filter(isPrimitiveCastable)
                                        // only add type cast for variables that are actually of different types
                                        .filter(v -> !dest.getType().equals(v.getType()))
                                        .map(v -> (Supplier<String>) () -> {
                                            dest.setInitialized();
                                            return Statement(assign(cast(v.access()).to(dest.getType().descriptor())).to(dest.access()));
                                        }))
        ).findFirst()
                .map(Supplier::get)
                .ifPresent(statement -> insertIntoMethodBody(method, statement));

    }
}
