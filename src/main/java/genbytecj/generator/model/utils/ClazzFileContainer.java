package genbytecj.generator.model.utils;



import genbytecj.generator.model.startpoint.GenerationController;
import genbytecj.generator.model.logger.ClazzLogger;
import genbytecj.generator.model.metamodel.exceptions.CompilationFailedException;
import genbytecj.generator.model.metamodel.resolvers.JavassistResolver;
import genbytecj.generator.model.metamodel.resolvers.Resolver;
import javassist.*;
import javassist.bytecode.ClassFilePrinter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;


public class ClazzFileContainer {

    private final CtClass clazz;
    private final ClazzLogger clazzLogger;
    private final RandomSupplier randomSupplier;
    private final String fileName;

    /**
     * The global resolver instance.
     */
    private final Resolver<String> resolver;

    public ClazzFileContainer(Random rand, GenerationController controller, String fileName) {
        this.clazz = ClassPool.getDefault().makeClass(fileName);

        this.resolver = new JavassistResolver();

        this.randomSupplier = new RandomSupplier(
                rand,
                controller.getMaxArrayDimensions().toInteger(),
                controller.getMaxArrayDimensionSize().toInteger(),
                controller.getPrimitiveTypesProbability().toBoolean(),
                controller.getObjectProbability().toBoolean(),
                controller.getArrayProbability().toBoolean(),
                controller.getVoidProbability().toBoolean()
        );

        this.fileName = fileName;
        try {
            CtMethod m = CtNewMethod.make(
                    "public static void main(String[] args) {}",
                    this.clazz);
            clazz.addMethod(m);
        } catch (CannotCompileException e) {
            throw new CompilationFailedException(e);
        }
        this.clazzLogger = ClazzLogger.generate(
                rand,
                fileName,
                randomSupplier
        );
    }

    public CtClass getClazzFile() {
        return clazz;
    }

    public ClazzLogger getClazzLogger() {
        return clazzLogger;
    }

    public RandomSupplier getRandomSupplier() {
        return randomSupplier;
    }

    public String getFileName() {
        return fileName;
    }

    public Resolver<String> resolver() {
        return resolver;
    }

    @Override
    public String toString() {
        try (StringWriter sw = new StringWriter();
             PrintWriter pw = new PrintWriter(sw)) {

            ClassFilePrinter.print(clazz.getClassFile(), pw);

            return sw.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
