package genbytecj.generator.model.metamodel.expressions.constants;


import genbytecj.generator.model.types.base.PrimitiveType;

/**
 * Primitive {@code byte} type constant.
 */
public final class ByteConstant implements Constant<Byte> {
    private final byte b;

    public ByteConstant(byte b) {
        this.b = b;
    }

    @Override
    public Byte value() {
        return b;
    }

    @Override
    public PrimitiveType<Byte> type() {
        return PrimitiveType.BYTE;
    }

    public byte raw() {
        return b;
    }
}
