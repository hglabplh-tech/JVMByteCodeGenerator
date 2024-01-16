package genbytecj.generator.model.types.base;

import genbytecj.generator.model.logger.FieldVarLogger;
import genbytecj.generator.model.metamodel.Builder;
import genbytecj.generator.model.utils.StatementDSL;

import java.util.Collections;
import java.util.List;


/**
 * Specialized {@link MetaType} for {@code void}.
 */
public enum VoidType implements MetaType<Void> {

    /**
     * Singleton instance.
     */
    VOID;

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
    public Kind kind() {
        return Kind.VOID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<Void> clazz() {
        return Void.TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String descriptor() {
        return StatementDSL.Patterns.VOID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHashCode(FieldVarLogger<Void> variable) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends MetaType<?>> getAssignableTypes() {
        // void is incompatible with everything
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAssignableFrom(MetaType<?> __) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Builder<Void>> builders() {
        // Void should not be built
        return Collections.emptyList();
    }

}
