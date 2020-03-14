package edu.upc.calculator;

import java.util.Vector;

interface CalculatorInterface {

    //Parses the text from strings and creates a list for easier arithmethic operation manipulation
    public boolean ExpressionVector_To_Result( Vector<ExpressionInterface> expressions_vector,boolean OperateInRadians);
    //Spits the result to the Corresponding Text Box on screen
    public Vector<ExpressionInterface> TertiaryExpressionsSolver(Vector<ExpressionInterface> ExpressionVector);
    public Vector<ExpressionInterface> SecondaryExpressionsSolver(Vector<ExpressionInterface> ExpressionVectorTertiarySolved);
    public boolean PrimaryExpressionsSolver(Vector<ExpressionInterface> ExpressionVectorSecondarySolver);
    public double get_Result();
    //Math Operations to find the solution
}
