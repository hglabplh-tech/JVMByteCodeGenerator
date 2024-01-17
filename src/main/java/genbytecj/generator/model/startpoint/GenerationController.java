package genbytecj.generator.model.startpoint;

import genbytecj.generator.model.generators.RandomCodeGenerator;
import genbytecj.generator.model.generators.SnippetGenerator;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

public class GenerationController {


    private final Map<LibOptions, ExecutionOps.OptValue<?>>
            controlValues = new ExecutionOps().getSettings();

    private boolean avoidOverflows = true;
    private boolean avoidDivByZero = true;

    private String fileName;
    private String location;



//    void setFileName(String fileName) {
//        this.fileName = fileName;
//    }
//
//    void setLocation(String location) {
//        this.location = location;
//    }

    public boolean avoidOverflows() {
        return avoidOverflows;
    }

    public void setAvoidOverflows(boolean avoidOverflows) {
        this.avoidOverflows = avoidOverflows;
    }

    public boolean avoidDivByZero() {
        return avoidDivByZero;
    }

    public void setAvoidDivByZero(boolean avoidDivByZero) {
        this.avoidDivByZero = avoidDivByZero;
    }

    public String getFileName() {
        return fileName;
    }

    public String getLocation() {
        return location;
    }

    public  ExecutionOps.OptValue<?> getLocalVariableProbability() {
        return  controlValues.get(LibOptions.LV);
    }

    public ExecutionOps.OptValue<?> getFieldProbability() {
        return controlValues.get(LibOptions.F);
    }

    public ExecutionOps.OptValue<?> getGlobalAssignProbability() {
        return controlValues.get(LibOptions.GA);
    }

    public ExecutionOps.OptValue<?> getLocalAssignProbability() {
        return controlValues.get(LibOptions.LA);
    }

    public ExecutionOps.OptValue<?> getMethodProbability() {
        return controlValues.get(LibOptions.M);
    }

    public ExecutionOps.OptValue<?>getMethodCallProbability() {
        return controlValues.get(LibOptions.MC);
    }

    public ExecutionOps.OptValue<?> getPrintProbability() {
        return controlValues.get(LibOptions.P);
    }

    public ExecutionOps.OptValue<?> getProgramLengthWeighting() {
        return controlValues.get(LibOptions.L);
    }

    public ExecutionOps.OptValue<?> getMethodLengthWeighting() {
        return controlValues.get(LibOptions.ML);
    }

    public ExecutionOps.OptValue<?>  getMaximumMethodParameters() {
        return controlValues.get(LibOptions.MP);
    }

    public ExecutionOps.OptValue<?> getMethodOverloadProbability() {
        return controlValues.get(LibOptions.MO);
    }

    public ExecutionOps.OptValue<?> getJavaLangMathProbability() {
        return controlValues.get(LibOptions.JLM);
    }

    public ExecutionOps.OptValue<?> getControlFlowProbability() {
        return controlValues.get(LibOptions.CF);
    }

    public ExecutionOps.OptValue<?> getControlLengthWeighting() {
        return controlValues.get(LibOptions.CL);
    }

    public ExecutionOps.OptValue<?> getControlFlowDeepness() {
        return controlValues.get(LibOptions.CD);
    }

    public ExecutionOps.OptValue<?> getIfBranchingFactor() {
        return controlValues.get(LibOptions.IBF);
    }

    public ExecutionOps.OptValue<?> getMaxLoopIterations() {
        return controlValues.get(LibOptions.MLI);
    }

    public ExecutionOps.OptValue<?> getWhileProbability() {
        return controlValues.get(LibOptions.WHILE);
    }

    public ExecutionOps.OptValue<?> getForProbability() {
        return controlValues.get(LibOptions.FOR);
    }

    public ExecutionOps.OptValue<?> getDoWhileProbability() {
        return controlValues.get(LibOptions.DOWHILE);
    }

    public ExecutionOps.OptValue<?> getIfProbability() {
        return controlValues.get(LibOptions.IF);
    }

    public ExecutionOps.OptValue<?> getOperatorStatementProbability() {
        return controlValues.get(LibOptions.OS);
    }

    public ExecutionOps.OptValue<?> getArithmeticProbability() {
        return controlValues.get(LibOptions.AS);
    }

    public ExecutionOps.OptValue<?> getLogicalProbability() {
        return controlValues.get(LibOptions.LS);
    }

    public ExecutionOps.OptValue<?> getBitwiseProbability() {
        return controlValues.get(LibOptions.BS);
    }

    public ExecutionOps.OptValue<?> getArithmeticLogicalProbability() {
        return controlValues.get(LibOptions.ALS);
    }

    public ExecutionOps.OptValue<?> getArithmeticBitwiseProbability() {
        return controlValues.get(LibOptions.ABS);
    }

    public ExecutionOps.OptValue<?> getLogicBitwiseProbability() {
        return controlValues.get(LibOptions.LBS);
    }

    public ExecutionOps.OptValue<?> getArithmeticLogicalBitwiseProbability() {
        return controlValues.get(LibOptions.ALBS);
    }

    public ExecutionOps.OptValue<?> getMaxOperators() {
        return controlValues.get(LibOptions.MOPS);
    }

    public ExecutionOps.OptValue<?> executeRunXTimes() {
        return controlValues.get(LibOptions.XRUNS);
    }

    public ExecutionOps.OptValue<?> getSnippetProbability() {
        return controlValues.get(LibOptions.SNIPPET);
    }

    public ExecutionOps.OptValue<?> getBreakProbability() {
        return controlValues.get(LibOptions.BREAK);
    }

    public ExecutionOps.OptValue<?> getPreemptiveReturnProbability() {
        return controlValues.get(LibOptions.RETURN);
    }

    public ExecutionOps.OptValue<?> getPrimitiveTypesProbability() {
        return controlValues.get(LibOptions.PRIMITIVES);
    }

    public ExecutionOps.OptValue<?> getObjectProbability() {
        return controlValues.get(LibOptions.OBJECTS);
    }

    public ExecutionOps.OptValue<?> getArrayProbability() {
        return controlValues.get(LibOptions.ARRAYS);
    }

    public ExecutionOps.OptValue<?> getVoidProbability() {
        return controlValues.get(LibOptions.VOID);
    }

    public ExecutionOps.OptValue<?> getMaxArrayDimensions() {
        return controlValues.get(LibOptions.MAXDIM);
    }

    public ExecutionOps.OptValue<?> getMaxArrayDimensionSize() {
        return controlValues.get(LibOptions.MAXDIMSIZE);
    }

    public ExecutionOps.OptValue<?> getTypeCastProbability() {
        return controlValues.get(LibOptions.CAST);
    }

    public ExecutionOps.OptValue<?> getSeedValue() {
        return controlValues.get(LibOptions.SEED);
    }

    public ExecutionOps.OptValue<?> getArrayAccessProbability() {
        return controlValues.get(LibOptions.ARRAY_ACCESS);
    }

    public ExecutionOps.OptValue<?> getArrayRestrictionProbability() {
        return controlValues.get(LibOptions.ARRAY_RESTRICTION);
    }

    public  SnippetGenerator initSnippetGen () throws IOException {
        Random rand = new Random(875687578);
        File tmpFile = File.createTempFile("tmpClazz" ,"class");
        RandomCodeGenerator rCodeGen = new RandomCodeGenerator(tmpFile.getAbsolutePath(), this);
        SnippetGenerator snippetGen = new SnippetGenerator(rand, rCodeGen);
        return snippetGen;


    }

    public  RandomCodeGenerator initRandCodeGen () throws IOException {
        Random rand = new Random(875687578);
        File tmpFile = File.createTempFile("tmpClazz" ,"class");
        RandomCodeGenerator rCodeGen = new RandomCodeGenerator(tmpFile.getAbsolutePath(), this);
        return rCodeGen;
    }
}
