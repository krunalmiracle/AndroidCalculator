package edu.upc.calculator;

import java.util.Vector;

interface CalculatorInterface {
    //Final Result
    double result = 0;
    //Parses the text from strings and creates a list for easier arithmethic operation manipulation
    public boolean Parse_list_to_operation(Vector<String> input);
    //Spits the result to the Corresponding Text Box on screen
    public double getResult();
    //Math Operations to find the solution
}
