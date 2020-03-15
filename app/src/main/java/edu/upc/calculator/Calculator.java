package edu.upc.calculator;

import android.widget.Switch;

import java.util.Vector;

public class Calculator implements CalculatorInterface {
    private double _result = 0;
    private boolean _radians = true;
    Calculator(){ }

    @Override //Implemented Expression Solver
    public boolean ExpressionVector_To_Result( Vector<ExpressionInterface> expressions_vector,boolean OperateInRadians){
        this._radians = OperateInRadians;
        Vector<ExpressionInterface> SolvedTertiaryExpressions_vector = new Vector<ExpressionInterface>() ;
        Vector<ExpressionInterface> SolvedSecondaryExpressions_vector;
        //Solve all the Tertiary Expressions from RHS Nullary & Primary Only no Secondary as no parenthesis
        SolvedSecondaryExpressions_vector = TertiaryExpressionsSolver(expressions_vector);
        if(SolvedSecondaryExpressions_vector==null )
            return false;
        //Solve all the Secondary Expressions using Nullary, Primaries
        SolvedSecondaryExpressions_vector = SecondaryExpressionsSolver(SolvedTertiaryExpressions_vector);
        if(SolvedSecondaryExpressions_vector==null )
            return false;
            //Solve all the Primary Expressions using Nullary
        boolean isSolved = PrimaryExpressionsSolver(SolvedSecondaryExpressions_vector);
        return isSolved;
    }

    @Override //WORKS Tertiary Solver
    public Vector<ExpressionInterface> TertiaryExpressionsSolver(Vector<ExpressionInterface> ExpressionVector)
    {
        Vector<ExpressionInterface> SolvedTertiaryExpressions_vector = new Vector<ExpressionInterface>() ;
        int i=0;
        while(i<ExpressionVector.size())
        {
            if(ExpressionVector.get(i).get_ExpressionLevel()==ExpressionLevel.Tertiary) {
                if (ExpressionVector.get(i + 1).get_ExpressionLevel() == ExpressionLevel.Nullary)//If the value after trig function is number,simple case
                {
                    double tmp = 0; double RHS = (Double) ExpressionVector.get(i + 1).get_Value();
                    if(!this._radians){RHS = Math.toRadians(RHS);} //Convert To Radians
                    switch (ExpressionVector.get(i).get_Value().toString())
                    {
                        case "Tan":
                            // code block
                            tmp = Math.tan(RHS);
                            break;
                        case "Cos":
                            // code block
                            tmp = Math.cos(RHS);
                            break;
                        case "Sin":
                            // code block
                            tmp = Math.sin(RHS);
                            break;
                        default:
                            return null; //Could not parse the correct Secondary Operator
                    }
                    SolvedTertiaryExpressions_vector.add(new Expression<Double>(tmp, ExpressionLevel.Nullary));
                    i++;
                }
                else //If the value after trig function is a primary operator (+ or -), than extra step
                {
                    double tmp = 0; double RHS = (Double) ExpressionVector.get(i + 2).get_Value();
                    if(ExpressionVector.get(i + 1).get_Value().toString().equals("-")){RHS = -RHS;} //Change Sign if negative present before Nullary(Number)
                    if(!this._radians){RHS = Math.toRadians(RHS);} //Convert To Radians
                    switch (ExpressionVector.get(i).get_Value().toString())
                    {
                        case "Tan":
                            // code block
                            tmp = Math.tan(RHS);
                            break;
                        case "Cos":
                            // code block
                            tmp = Math.cos(RHS);
                            break;
                        case "Sin":
                            // code block
                            tmp = Math.sin(RHS);
                            break;
                        default:
                            return null; //Could not parse the correct Secondary Operator
                    }
                    SolvedTertiaryExpressions_vector.add(new Expression<Double>(tmp, ExpressionLevel.Nullary));
                    i++;i++;
                }
            }
            else //If not Tertiary than add normal
            {
                SolvedTertiaryExpressions_vector.add(ExpressionVector.get(i));
            }
            i++;
        }
        return SolvedTertiaryExpressions_vector;
    }

    @Override //WORKS Secondary Solver
    public Vector<ExpressionInterface> SecondaryExpressionsSolver(Vector<ExpressionInterface> ExpressionVectorTertiarySolved) {
        Vector<ExpressionInterface> SolvedSecondaryExpressions_vector = new Vector<ExpressionInterface>() ;
        int i=0;boolean isGroupedSecondaryExpression = true;
        while(i<ExpressionVectorTertiarySolved.size()-1)
        {
            if(ExpressionVectorTertiarySolved.get(i).get_ExpressionLevel()==ExpressionLevel.Secondary)
            {
                    if(ExpressionVectorTertiarySolved.get(i+1).get_ExpressionLevel()==ExpressionLevel.Nullary)
                    {
                        //We can directly solve as its just a number now
                        double tmp_val = 0;double LHS = (Double) ExpressionVectorTertiarySolved.get(i - 1).get_Value();
                        isGroupedSecondaryExpression = false;
                        if(i>2 ) //We aren't on the first expression
                        {
                            if (ExpressionVectorTertiarySolved.get(i - 2).get_ExpressionLevel() == ExpressionLevel.Secondary) //This isn't the first Secondary Operator
                            {
                                LHS = (Double) SolvedSecondaryExpressions_vector.get(SolvedSecondaryExpressions_vector.size() - 1).get_Value();
                                isGroupedSecondaryExpression = true;
                            }
                        }
                        double RHS = (Double) ExpressionVectorTertiarySolved.get(i + 1).get_Value();
                        switch (ExpressionVectorTertiarySolved.get(i).get_Value().toString())
                        {
                            case "*":
                                // code block
                                tmp_val = LHS * RHS;
                                break;
                            case "/":
                                // code block
                                tmp_val = LHS / RHS;
                                break;
                            default:
                                return null; //Could not parse the correct Secondary Operator
                        }
                        if(!isGroupedSecondaryExpression) //Not a grouped Second OrderMultiplication,at least not until now
                        {
                            SolvedSecondaryExpressions_vector.add(new Expression<Double>(tmp_val, ExpressionLevel.Nullary));
                        }
                        else //The Last operation was also Secondary Operation, so we have to substitute with the proper value
                        {
                            Expression tmp_expression = new Expression<Double>(tmp_val,ExpressionLevel.Nullary); //Numeric Value
                            SolvedSecondaryExpressions_vector.setElementAt(tmp_expression, SolvedSecondaryExpressions_vector.size() - 1);
                        }
                        i++;//Jumped one Places
                    }
            }
            else if(ExpressionVectorTertiarySolved.get(i+1).get_ExpressionLevel()!=ExpressionLevel.Secondary)//A primary Expression or Nullary Expression
            {
                SolvedSecondaryExpressions_vector.add(ExpressionVectorTertiarySolved.get(i));
                i++;
            }
            i++;
        }
        if(SolvedSecondaryExpressions_vector.size()==0) //Means that the SolvedTertiary didn't had any second degree Expressions
        {
            SolvedSecondaryExpressions_vector.addAll(0,ExpressionVectorTertiarySolved);
        }
        return SolvedSecondaryExpressions_vector;
    }

    @Override //WORKS Primary Solver
    public boolean PrimaryExpressionsSolver(Vector<ExpressionInterface> ExpressionVectorSecondarySolved) {
        double SolvedPrimaryExpression_Value = 0;
        double LHS = 0;
        double RHS = 0;
        double tmp = 0;
        int i=0;
        //LOGIC
        if (ExpressionVectorSecondarySolved.get(0).get_Value().toString().equals("+"))//Positive Operator as First Expression
        {
            LHS = (double) ExpressionVectorSecondarySolved.get(1).get_Value();
            i++;i++;
        }
        else if(ExpressionVectorSecondarySolved.get(0).get_Value().toString().equals("-")) //Negative Operator as First Expression
        {
            LHS = (double) ExpressionVectorSecondarySolved.get(1).get_Value();
            LHS= -LHS;
            i++;i++;
        }
        else{
            LHS = (double) ExpressionVectorSecondarySolved.get(0).get_Value();
            i++;
        }
        while(i<ExpressionVectorSecondarySolved.size())
        {
            if(ExpressionVectorSecondarySolved.get(i).get_ExpressionLevel()==ExpressionLevel.Primary) //We are on a operator
            {
                RHS = (double) ExpressionVectorSecondarySolved.get(i+1).get_Value();
                if(ExpressionVectorSecondarySolved.get(i).get_Value().toString().equals("+"))//We have a addition
                {
                    tmp = LHS + RHS;
                }
                else if(ExpressionVectorSecondarySolved.get(i).get_Value().toString().equals("-"))//We have a addition
                {
                    tmp = LHS - RHS;
                }
                LHS = tmp;
                i++;//Due to Look Ahead Addition a+b
            }
            i++;
        }
        this._result = LHS;
        return true;
    }

    @Override //Gets Result
    public double get_Result(){
        return this._result;
    }
}
