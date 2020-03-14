package edu.upc.calculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private Calculator calculator_test_object;
    private String[] array_of_math_elements;
    @Before
    public void setUp(){
        calculator_test_object = new Calculator();
        array_of_math_elements =new String[]{"2","*","2"};
    }
    @Test
    public void check_operations_list_to_result(){

        assertEquals(4,calculator_test_object.Arithmethic_operator(new String[]{"2","*","2"}),0);
        assertEquals(2,calculator_test_object.Arithmethic_operator(new String[]{"2","*"}),0);
        assertEquals(8,calculator_test_object.Arithmethic_operator(new String[]{"2","+","3","*","2"}),0);;

    }
    @Test
    public void check_conversion_string_to_list(){
        assertEquals(new String[]{"2","+","3","*","2"},calculator_test_object.Parse_text_to_list("2+3*2"));

        assertEquals(new String[]{"2","+","3","*"},calculator_test_object.Parse_text_to_list("2+3*"));
    }
}