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
    private Vector<String> test_list = new Vector<String>();
    private boolean test;
    @Before
    public void setUp(){
        calculator_test_object = new Calculator();
        test_list.add("2");
        test_list.add("+");
        test_list.add("2");
        test = calculator_test_object.Parse_list_to_operation(test_list);
    }
    @Test
    public void vector_str_to_num_result(){
        if(test)
            assertEquals(4,calculator_test_object.getResult(),0);
    }

}