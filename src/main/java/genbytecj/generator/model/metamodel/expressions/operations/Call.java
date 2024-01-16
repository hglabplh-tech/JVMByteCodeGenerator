package genbytecj.generator.model.metamodel.expressions.operations;


import genbytecj.generator.model.metamodel.expressions.Expression;

import java.util.List;

/**
 * Describes a generic call expression.
 *
 * @param <T> The Java class that describes the result of this method (or void)
 */
public interface Call<T> extends Expression<T> {

    /**
     * Returns the name of the method (without sender or arguments).
     *
     * @return a string containing the name of the method
     */
    String name();

    /**
     * Returns the sender expression.
     *
     * @return the expression on which this method is called
     */
    Expression<?> sender();

    /**
     * Returns the argument list.
     *
     * @return the list of expressions that are passed as arguments
     */
    List<? extends Expression<?>> arguments();
}
