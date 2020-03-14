package edu.upc.calculator;

public class Expression <T> implements ExpressionInterface <T> {

    private T _value;
    private ExpressionLevel _expressionLevel;
    public Expression(T Value, ExpressionLevel expressionLevel){
        this._value = Value;
        this._expressionLevel = expressionLevel;
    }
    public ExpressionLevel get_ExpressionLevel(){
        return this._expressionLevel;
    }
    public T get_Value(){
        return this._value;
    }
    public void set_value(T Value) {
        this._value = Value;
    }
    public void set_ExpressionLevel(ExpressionLevel expressionLevel) {
        this._expressionLevel = expressionLevel;
    }
}
