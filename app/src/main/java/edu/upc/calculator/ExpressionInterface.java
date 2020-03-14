package edu.upc.calculator;

public interface ExpressionInterface<T> {
    ExpressionLevel _type = null;
    public T get_Value();
    public void set_value(T Value);
    public void set_ExpressionLevel(ExpressionLevel expressionLevel);
    public ExpressionLevel get_ExpressionLevel();
}
