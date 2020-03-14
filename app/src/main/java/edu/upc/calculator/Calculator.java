package edu.upc.calculator;

import android.widget.Switch;

import java.util.Vector;

public class Calculator implements CalculatorInterface {
    private double _result = 0;
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

    @Override
    public Vector<ExpressionInterface> TertiaryExpressionsSolver(Vector<ExpressionInterface> ExpressionVector)
    {
        Vector<ExpressionInterface> SolvedTertiaryExpressions_vector = new Vector<ExpressionInterface>() ;
        int i=0;
        while(i<ExpressionVector.size())
        {
            if(ExpressionVector.get(i).get_ExpressionLevel()==ExpressionLevel.Tertiary)
            {
                if(ExpressionVector.get(i+1).get_ExpressionLevel()==ExpressionLevel.Nullary)
                {
                    //We can directly solve as its just a number now
                    double tmp_val = 0;double tmp_val2 = (Double) ExpressionVector.get(i+1).get_Value();
                    if(!this._radians)
                        tmp_val2 = Math.toRadians((Double) ExpressionVector.get(i+1).get_Value());
                    switch(ExpressionVector.get(i).get_Value().toString())
                    {
                        case "Tan":
                            // code block
                            tmp_val = Math.tan(tmp_val2);
                            break;
                        case "Sin":
                            // code block
                            tmp_val = Math.sin(tmp_val2);
                            break;
                        case "Cos":
                            // code block
                            tmp_val = Math.cos(tmp_val2);
                            break;
                        default:
                            return null; //Could not parse the correct tertiary
                    }
                    SolvedTertiaryExpressions_vector.add(new Expression<Double>(tmp_val, ExpressionLevel.Nullary));
                    i++;//As we have jumped one place
                }
                else if(ExpressionVector.get(i+1).get_ExpressionLevel()==ExpressionLevel.Primary)
                {
                    if(ExpressionVector.get(i+2).get_ExpressionLevel()==ExpressionLevel.Nullary)
                    {
                        if(ExpressionVector.get(i+1).get_Value()=="+")  //For Positive Operand
                        {
                            //We can Solve Now directly as its positive nullary
                            double tmp_val = 0;
                            double tmp_val2 = (Double) ExpressionVector.get(i + 2).get_Value(); //No need to add anything
                            if (!this._radians)
                                tmp_val2 = Math.toRadians((Double) ExpressionVector.get(i + 2).get_Value());
                            switch (ExpressionVector.get(i).get_Value().toString()) {
                                case "Tan":
                                    // code block
                                    tmp_val = Math.tan(tmp_val2);
                                    break;
                                case "Sin":
                                    // code block
                                    tmp_val = Math.sin(tmp_val2);
                                    break;
                                case "Cos":
                                    // code block
                                    tmp_val = Math.cos(tmp_val2);
                                    break;
                                default:
                                    return null; //Could not parse the correct tertiary
                            }
                            SolvedTertiaryExpressions_vector.add(new Expression<Double>(tmp_val, ExpressionLevel.Nullary));
                            i++;i++;//As two places we have jumped for calculation
                        }
                        else //For negative operand
                        {
                            //We can Solve Now directly as its negative nullary
                            double tmp_val = 0;
                            double tmp_val2 = -(Double) ExpressionVector.get(i + 2).get_Value(); //Added Negative
                            if (!this._radians)
                                tmp_val2 = Math.toRadians((Double) ExpressionVector.get(i + 2).get_Value());
                            switch (ExpressionVector.get(i).get_Value().toString()) {
                                case "Tan":
                                    // code block
                                    tmp_val = Math.tan(tmp_val2);
                                    break;
                                case "Sin":
                                    // code block
                                    tmp_val = Math.sin(tmp_val2);
                                    break;
                                case "Cos":
                                    // code block
                                    tmp_val = Math.cos(tmp_val2);
                                    break;
                                default:
                                    return null; //Could not parse the correct tertiary
                            }
                            SolvedTertiaryExpressions_vector.add(new Expression<Double>(tmp_val, ExpressionLevel.Nullary));
                            i++;i++;//As two places we have jumped for calculation
                        }
                    }
                    else{//We have bad expression
                        return null;}
                }
            }
            else{ //A primary Expression or Secondary Expression
                SolvedTertiaryExpressions_vector.add(ExpressionVector.get(i));
            }
            i++;
        }
        return SolvedTertiaryExpressions_vector;
    }

    @Override
    public Vector<ExpressionInterface> SecondaryExpressionsSolver(Vector<ExpressionInterface> ExpressionVectorTertiarySolved) {
        Vector<ExpressionInterface> SolvedSecondaryExpressions_vector = new Vector<ExpressionInterface>() ;
        int i=0;
        while(i<ExpressionVectorTertiarySolved.size()-1)
        {
            if(ExpressionVectorTertiarySolved.get(i).get_ExpressionLevel()==ExpressionLevel.Secondary)
            {
                    if(ExpressionVectorTertiarySolved.get(i+1).get_ExpressionLevel()==ExpressionLevel.Nullary)
                    {
                        //We can directly solve as its just a number now
                        double tmp_val = 0;
                        double LHS = (Double) ExpressionVectorTertiarySolved.get(i - 1).get_Value();
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
                        SolvedSecondaryExpressions_vector.add(new Expression<Double>(tmp_val, ExpressionLevel.Nullary));
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

    @Override
    public double get_Result(){
        return this._result;
    }
}
