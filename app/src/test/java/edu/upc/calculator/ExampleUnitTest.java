package edu.upc.calculator;

import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private Calculator calculator_test_object;
    private Vector<ExpressionInterface> expressions_vector ;
    private boolean _test;
    @Before
    public void setUp(){
        calculator_test_object = new Calculator();
        expressions_vector = new Vector<ExpressionInterface>();
    }
    private void AddExpression(Object Value, ExpressionLevel expressionLevel) {
        //Expression<Double> expression_tmp = new Expression<Double>(22.2,ExpressionLevel.Primary);
        ExpressionInterface tmp_exp = new Expression<Object>(Value, expressionLevel);
        expressions_vector.add(tmp_exp);
    }
    @Test
    public void PrimaryExpressionSolverTest(){
        //Simple Case 2+2
        double tmp = 2.0;
        String temp_str = "+";
        AddExpression(tmp,ExpressionLevel.Nullary);
        AddExpression(temp_str,ExpressionLevel.Primary);
        AddExpression(tmp,ExpressionLevel.Nullary);
        _test = calculator_test_object.PrimaryExpressionsSolver(expressions_vector);
        if (_test){assertEquals(4,calculator_test_object.get_Result(),0);}
        //Case +2-2
        tmp = 2.0;
        temp_str = "+";
        this.expressions_vector.clear();
        AddExpression(temp_str,ExpressionLevel.Primary);
        AddExpression(tmp,ExpressionLevel.Nullary);
        temp_str = "-";
        AddExpression(temp_str,ExpressionLevel.Primary);
        AddExpression(tmp,ExpressionLevel.Nullary);
        _test = calculator_test_object.PrimaryExpressionsSolver(expressions_vector);
        if (_test){assertEquals(0,calculator_test_object.get_Result(),0);}
        //Hard Case -2-2+2-2
        tmp = 2.0;
        temp_str = "-";
        this.expressions_vector.clear();
        AddExpression(temp_str,ExpressionLevel.Primary);
        AddExpression(tmp,ExpressionLevel.Nullary);
        temp_str = "-";
        AddExpression(temp_str,ExpressionLevel.Primary);
        AddExpression(tmp,ExpressionLevel.Nullary);
        temp_str = "+";
        AddExpression(temp_str,ExpressionLevel.Primary);
        AddExpression(tmp,ExpressionLevel.Nullary);
        temp_str = "-";
        AddExpression(temp_str,ExpressionLevel.Primary);
        AddExpression(tmp,ExpressionLevel.Nullary);
        _test = calculator_test_object.PrimaryExpressionsSolver(expressions_vector);
        if (_test){assertEquals(-4,calculator_test_object.get_Result(),0);}
    }
    @Test
    public void SecondaryExpressionSolverTest(){
        //Simple Case 2*2
        double tmp = 2.0;
        String temp_str = "*";
        AddExpression(tmp,ExpressionLevel.Nullary);
        AddExpression(temp_str,ExpressionLevel.Secondary);
        AddExpression(tmp,ExpressionLevel.Nullary);
        Vector<ExpressionInterface> tmp_vector = calculator_test_object.SecondaryExpressionsSolver(expressions_vector);
        _test = calculator_test_object.PrimaryExpressionsSolver(tmp_vector);
        if (_test){assertEquals(4,calculator_test_object.get_Result(),0);}

    }

}