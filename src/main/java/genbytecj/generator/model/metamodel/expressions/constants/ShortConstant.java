package genbytecj.generator.model.metamodel.expressions.constants;


import genbytecj.generator.model.types.base.PrimitiveType;

/**
 * Primitive {@code short} type constant.
 */
public final class ShortConstant implements Constant<Short> {
    private final short s;

    public ShortConstant(short s) {
        this.s = s;
    }

    @Override
    public Short value() {
        return s;
    }

    @Override
    public PrimitiveType<Short> type() {
        return PrimitiveType.SHORT;
    }

    public short raw() {
        return s;
    }
}
