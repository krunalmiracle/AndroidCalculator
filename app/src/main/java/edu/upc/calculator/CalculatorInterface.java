package edu.upc.calculator;

import java.util.Vector;

interface CalculatorInterface {

    //Parses the text from strings and creates a list for easier arithmethic operation manipulation
    public boolean ExpressionVector_To_Result( Vector<ExpressionInterface> expressions_vector,boolean OperateInRadians);
    //Spits the result to the Corresponding Text Box on screen
    public double get_Result();
    //Math Operations to find the solution
}
