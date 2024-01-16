package genbytecj.generator.model.types.specializations;



import genbytecj.generator.model.logger.FieldVarLogger;
import genbytecj.generator.model.metamodel.Builder;
import genbytecj.generator.model.metamodel.builders.ConstructorBuilder;
import genbytecj.generator.model.metamodel.builders.LibMethod;
import genbytecj.generator.model.metamodel.builders.NullBuilder;
import genbytecj.generator.model.metamodel.expressions.Expression;
import genbytecj.generator.model.types.base.MetaType;
import genbytecj.generator.model.types.base.RefType;

import java.util.Collections;
import java.util.List;


import static genbytecj.generator.model.utils.StatementDSL.Conditions.notNull;
import static genbytecj.generator.model.utils.StatementDSL.method;
import static genbytecj.generator.model.utils.StatementDSL.ternary;
import static java.util.Arrays.asList;

/**
 * Specialized {@link String} meta type.
 */
public enum StringType implements RefType<String> {

    /**
     * {@link String} type constant.
     */
    STRING;

    /**
     * The methods that are available for this type.
     */
    private final List<LibMethod<?>> methods;

    StringType() {
        methods = inferMethods();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<String> clazz() {
        return String.class;
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
    public String getHashCode(FieldVarLogger<String> variable) {
        assert variable != null;

        String name = variable.access();

        return ternary(
                notNull(name),
                method(name, "hashCode"),
                "0L"
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAssignableFrom(MetaType<?> other) {
        // string is only assignable from other strings
        return this == other;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends RefType<?>> getAssignableTypes() {
        return Collections.singletonList(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LibMethod<?>> methods() {
        return methods;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Builder<String>> builders() {
        return asList(
                new NullBuilder<>(this),
                new ConstructorBuilder<>(this),
                // direct initialization (i.e. String str = "...")
                new Builder<String>() {
                    @Override
                    public List<? extends MetaType<?>> requires() {
                        return Collections.singletonList(STRING);
                    }

                    @SuppressWarnings("unchecked")
                    @Override
                    public Expression<String> build(List<? extends Expression<?>> params) {
                        assert params.size() == 1;
                        return (Expression<String>) params.get(0);
                    }

                    @Override
                    public StringType returns() {
                        return StringType.this;
                    }
                }
        );
    }
}
