package genbytecj.generator.model.metamodel.expressions.constants;


import genbytecj.generator.model.types.base.PrimitiveType;

/**
 * Primitive {@code double} type constant.
 */
public final class DoubleConstant implements Constant<Double> {
    private final double d;

    public DoubleConstant(double d) {
        this.d = d;
    }

    @Override
    public Double value() {
        return d;
    }

    @Override
    public PrimitiveType<Double> type() {
        return PrimitiveType.DOUBLE;
    }

    public double raw() {
        return d;
    }
}
