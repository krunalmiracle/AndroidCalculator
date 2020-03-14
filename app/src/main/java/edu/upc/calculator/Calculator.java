package edu.upc.calculator;

import java.util.Vector;

public class Calculator implements CalculatorInterface {
    private double current_result = 0;
    private boolean _radians = true;
    Calculator(){ }
    //FOR LATER USE!! REMEMBER TO REMOVE!!
    /*else if(this._expressions_vector.get(last_Element_index).get_ExpressionLevel() == ExpressionLevel.Primary &&(tmp_value=="+" ||tmp_value =="-")){
                        //It means we need to descend the state of the Addition and Subtraction Operator to lower Level(Nullary) and append the digit
                        String tmp_str = this._expressions_vector.lastElement().get_Value().toString() + digit;
                        double  tmp_double = Double.parseDouble(tmp_str);
                        Expression tmp_expression = new Expression<Double>(tmp_double,ExpressionLevel.Nullary);
                        this._expressions_vector.setElementAt(tmp_expression, last_Element_index);
                    }*/
    @Override
    public boolean ExpressionVector_To_Result( Vector<ExpressionInterface> expressions_vector,boolean OperateInRadians){
        this._radians = OperateInRadians;

            //Solve all the Tertiary Expressions from RHS Nullary

            //Solve all the Secondary Expressions using Nullary

            //Solve all the Primary Expressions using Nullary

        return true;
    }
    @Override
    public double get_Result(){
        return current_result;
    }
}
