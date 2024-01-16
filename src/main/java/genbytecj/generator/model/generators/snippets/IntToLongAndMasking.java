package genbytecj.generator.model.generators.snippets;


import genbytecj.generator.model.logger.FieldVarLogger;
import genbytecj.generator.model.logger.MethodLogger;
import genbytecj.generator.model.metamodel.expressions.Assignment;
import genbytecj.generator.model.metamodel.expressions.Expression;
import genbytecj.generator.model.metamodel.expressions.constants.LongConstant;
import genbytecj.generator.model.metamodel.expressions.operations.BinaryOp;
import genbytecj.generator.model.metamodel.expressions.operations.TypeCast;
import genbytecj.generator.model.types.base.PrimitiveType;
import genbytecj.generator.model.utils.ClazzFileContainer;
import genbytecj.generator.model.utils.Operator;
import genbytecj.generator.model.utils.RandomSupplier;

import static genbytecj.generator.model.metamodel.expressions.Expression.NOP.NOP;
import static genbytecj.generator.model.utils.StatementDSL.Statement;

/**
 * Generates a code snippet that takes an integer,
 * casts it to long and then masks it with 32bit.
 */
public class IntToLongAndMasking implements Snippet {

    /**
     * The bitmask to apply in order to get the "int" value.
     */
    private static final long MASK = 0xFF_FF_FF_FF;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPossible(MethodLogger method) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public String generate(RandomSupplier supplier, ClazzFileContainer container, MethodLogger context) {
        Expression<Integer> intExpr =
                container.getClazzLogger().valueOf(PrimitiveType.INT, context);

        Expression<Long> longExpr = new TypeCast<>(PrimitiveType.LONG, intExpr);

        Expression<Long> maskedExpr = new BinaryOp<>(
                PrimitiveType.LONG,
                Operator.BIT_AND,
                longExpr,
                new LongConstant(MASK)
        );

        return Statement(
                container.resolver().resolve(
                        container.getClazzLogger()
                                .getNonFinalVarsUsableInMethod(context)
                                .filter(v -> v.getType() == PrimitiveType.LONG)
                                .findFirst()
                                .<Expression<?>>map(d ->
                                        new Assignment<>(
                                                (FieldVarLogger<Long>) d,
                                                maskedExpr))
                                .orElse(NOP)
                )
        );
    }
}
