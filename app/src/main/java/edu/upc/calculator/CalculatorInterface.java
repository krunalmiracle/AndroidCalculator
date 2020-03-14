package edu.upc.calculator;

interface CalculatorInterface {
    //Final Result
    double result = 0;

    //Adds the current value and the last value together
    public double Arithmethic_operator( String[] List_of_operations_and_values);
    //Parses the text from strings and creates a list for easier arithmethic operation manipulation
    public String[] Parse_text_to_list(String text_);
    //Spits the result to the Corresponding Text Box on screen
    public void Write_to_TextBox(String write_text);
    //Math Operations to find the solution

}
