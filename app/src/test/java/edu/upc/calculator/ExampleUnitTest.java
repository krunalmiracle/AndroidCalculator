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
    private Vector<ExpressionInterface> expressions_vector;
    private boolean _test;
    @Before
    public void setUp(){
        calculator_test_object = new Calculator();

        _test = calculator_test_object.ExpressionVector_To_Result(expressions_vector,true);
    }
    @Test
    public void vector_str_to_num_result(){
        if(_test)
           assertEquals(4,calculator_test_object.get_Result(),0);
    }

}