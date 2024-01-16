package genbytecj.generator.model.metamodel.expressions.constants;


import genbytecj.generator.model.types.base.MetaType;
import genbytecj.generator.model.types.specializations.StringType;

/**
 * A constant expression of type {@link String}.
 */
public class StringConstant implements Constant<String> {

    private final String str;

    public StringConstant(String str) {
        this.str = str;
    }

    @Override
    public String value() {
        return str;
    }

    @Override
    public MetaType<String> type() {
        return StringType.STRING;
    }
}
