package genbytecj.generator.model.metamodel.resolvers;




import genbytecj.generator.model.logger.FieldVarLogger;
import genbytecj.generator.model.metamodel.expressions.Assignment;
import genbytecj.generator.model.metamodel.expressions.Expression;
import genbytecj.generator.model.metamodel.expressions.TypeIdentifier;
import genbytecj.generator.model.metamodel.expressions.constants.*;
import genbytecj.generator.model.metamodel.expressions.operations.*;
import genbytecj.generator.model.types.base.MetaType;
import genbytecj.generator.model.types.base.VoidType;

import java.util.stream.Collectors;

import static genbytecj.generator.model.utils.StatementDSL.*;
import static genbytecj.generator.model.utils.StatementDSL.Assignments.assign;
import static genbytecj.generator.model.utils.StatementDSL.Casts.cast;
import static genbytecj.generator.model.utils.StatementDSL.Patterns.*;


/**
 * {@link Resolver} implementation that interprets expressions by
 * forming Javassist strings / expressions (i.e. actual source code).
 */
public class JavassistResolver implements Resolver<String> {

    @Override
    public <T> String resolve(TypeIdentifier<T> typeIdentifier) {
        MetaType<? extends T> type = typeIdentifier.type();

        if (type == VoidType.VOID)
            return VOID;

        return type.descriptor();
    }

    @Override
    public <T> String resolve(ArrayInit<T> arrayInit) {
        return NewArray(
                arrayInit.type().descriptor(),
                arrayInit.arguments().stream()
                        .map(this::resolve)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public <U> String resolve(ConstructorCall<U> constructorCall) {
        return New(
                constructorCall.type().descriptor(),
                constructorCall.arguments().stream()
                        .map(this::resolve)
                        .collect(Collectors.joining())
        );
    }

    @Override
    public <U> String resolve(FieldVarLogger<U> fieldVarLogger) {
        return variable(
                fieldVarLogger.isStatic()
                        ? fieldVarLogger.getClazz()
                        : fieldVarLogger.isField() ? "this" : "",
                fieldVarLogger.name
        );
    }

    @Override
    public <U> String resolve(MethodCall<U> methodCall) {
        return method(
                resolve(methodCall.sender()),
                methodCall.name(),
                methodCall.arguments().stream()
                        .map(this::resolve)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public <U> String resolve(MethodCall.Static<U> methodCall) {
        return resolve((MethodCall<U>) methodCall);
    }

    @Override
    public <U> String resolve(Assignment<U> assignment) {
        return assign(resolve(assignment.src()))
                .to(resolve(assignment.dest()));
    }

    @Override
    public String resolve(ByteConstant constant) {
        return cast(constant.raw()).to(byte.class);
    }

    @Override
    public String resolve(ShortConstant constant) {
        return cast(constant.raw()).to(short.class);
    }

    @Override
    public String resolve(IntConstant constant) {
        return String.valueOf(constant.raw());
    }

    @Override
    public String resolve(LongConstant constant) {
        return constant.raw() + "L";
    }

    @Override
    public String resolve(FloatConstant constant) {
        return constant.raw() + "F";
    }

    @Override
    public String resolve(DoubleConstant constant) {
        return constant.raw() + "D";
    }

    @Override
    public String resolve(BooleanConstant constant) {
        return String.valueOf(constant.raw());
    }

    @Override
    public String resolve(StringConstant constant) {
        return constant.value();
    }

    @Override
    public <U> String resolve(NullConstant<U> constant) {
        // add cast to signal to prevent ambiguous method calls
        // e.g. `foo(null)` could invoke
        // `foo(java.lang.String)` or `foo(java.lang.Object)`
        return cast(NULL).to(constant.type().descriptor());
    }

    @Override
    public String resolve(CharConstant constant) {
        return asChar(constant.raw());
    }

    @Override
    public <U> String resolve(TypeCast<U> typeCast) {
        return inPar(
                cast(resolve(typeCast.expression()))
                        .to(typeCast.type().descriptor())
        );
    }

    @Override
    public <U> String resolve(BinaryOp<U> binaryOp) {
        return inPar(
                resolve(binaryOp.a()) +
                        binaryOp.op().toString() +
                        resolve(binaryOp.b())
        );
    }

    @Override
    public String resolve(Expression.NOP nop) {
        return NOP;
    }
}
