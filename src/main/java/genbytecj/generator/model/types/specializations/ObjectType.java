package genbytecj.generator.model.types.specializations;


import genbytecj.generator.model.logger.FieldVarLogger;
import genbytecj.generator.model.metamodel.builders.LibMethod;
import genbytecj.generator.model.metamodel.builders.MethodBuilder;
import genbytecj.generator.model.types.TypeCache;
import genbytecj.generator.model.types.base.MetaType;
import genbytecj.generator.model.types.base.RefType;

import java.util.List;
import java.util.stream.Collectors;

import static genbytecj.generator.model.utils.StatementDSL.Conditions.notNull;
import static genbytecj.generator.model.utils.StatementDSL.method;
import static genbytecj.generator.model.utils.StatementDSL.ternary;


/**
 * Specialized {@link Object} meta type.
 */
public enum ObjectType implements RefType<Object> {

    /**
     * Singleton.
     */
    OBJECT;

    /**
     * The methods that are available for this type.
     */
    private final List<LibMethod<?>> methods;

    ObjectType() {
        methods = inferMethods();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<Object> clazz() {
        return Object.class;
    }

    @Override
    public String toString() {
        return descriptor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAssignableFrom(MetaType<?> other) {
        return other instanceof RefType<?>;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHashCode(FieldVarLogger<Object> variable) {
        String name = variable.access();

        return ternary(
                notNull(name),
                method(method(method(name, "getClass"), "getSimpleName"), "hashCode"),
                "0L"
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends RefType<?>> getAssignableTypes() {
        return TypeCache.CACHE.refTypes().collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends MethodBuilder<?>> methods() {
        return methods;
    }
}
