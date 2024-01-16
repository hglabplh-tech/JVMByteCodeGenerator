package genbytecj.generator.model.types.specializations;



import genbytecj.generator.model.logger.FieldVarLogger;
import genbytecj.generator.model.metamodel.Builder;
import genbytecj.generator.model.metamodel.builders.ConstructorBuilder;
import genbytecj.generator.model.metamodel.builders.LibMethod;
import genbytecj.generator.model.metamodel.builders.MethodBuilder;
import genbytecj.generator.model.metamodel.builders.NullBuilder;
import genbytecj.generator.model.types.base.PrimitiveType;
import genbytecj.generator.model.types.base.RefType;

import java.util.Date;
import java.util.List;


import static genbytecj.generator.model.utils.StatementDSL.Conditions.notNull;
import static genbytecj.generator.model.utils.StatementDSL.method;
import static genbytecj.generator.model.utils.StatementDSL.ternary;
import static java.util.Arrays.asList;

/**
 * Defines the specialized meta type for {@link Date}.
 */
public enum DateType implements RefType<Date> {

    /**
     * Singleton.
     */
    DATE;

    /**
     * The methods that are available for this type.
     */
    private final List<LibMethod<?>> methods;

    DateType() {
        this.methods = inferMethods();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<Date> clazz() {
        return Date.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHashCode(FieldVarLogger<Date> variable) {
        assert variable != null;

        String name = variable.access();

        return ternary(
                notNull(name),
                method(name, "getTime"),
                "0L"
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return descriptor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends MethodBuilder<?>> methods() {
        return methods;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Builder<Date>> builders() {
        return asList(
                new NullBuilder<>(this),
                // new Date(long)
                new ConstructorBuilder<>(this, PrimitiveType.LONG)
        );
    }
}
