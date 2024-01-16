package genbytecj.generator.model.metamodel.expressions.constants;


import genbytecj.generator.model.types.base.PrimitiveType;

/**
 * Primitive {@code float} type constant.
 */
public final class FloatConstant implements Constant<Float> {
    private final float f;

    public FloatConstant(float f) {
        this.f = f;
    }

    @Override
    public Float value() {
        return f;
    }

    @Override
    public PrimitiveType<Float> type() {
        return PrimitiveType.FLOAT;
    }

    public float raw() {
        return f;
    }
}
