package edu.upc.calculator;

import java.util.Vector;

public class Calculator implements CalculatorInterface {
    private double current_result = 0;
    private boolean radians = true;
    Calculator()
    {
        //Empty Constructor
    }

    @Override
    public boolean Parse_List_To_Result(Vector<String> input){
        //Divide the list
            //Search for
            int first_element = 0;
            Vector<Integer> OP_INDX = new Vector<Integer>();

            for(int i=0;i<input.size();i++)
            {
                if(input.get(i).equals("+") || input.get(i).equals("-")|| input.get(i).equals("/")|| input.get(i).equals("*")) {
                    //It means we have a operation and all the elements to the left and right are number, until next operation
                    OP_INDX.add(i);
                }
            }
            if(OP_INDX.size()==0) //We only have numbers and so the result are the same Numbers.
            {
                current_result = Double.parseDouble(input.lastElement());
                return true;
            }
            double LHS;
            LHS = Double.parseDouble(input.get(OP_INDX.get(0)-1));
            double RHS;
            for(int k = 0 ;k<OP_INDX.size();k++) //Assuming we always have multiple operand and no priority
            {
                //Implementing Multiple Digits
                //We can now do expression based math!!
                RHS = Double.parseDouble(input.get(OP_INDX.get(k)+1));
                switch (input.get(OP_INDX.get(k)))
                {
                    case "+":
                        // Addition
                        current_result = LHS + RHS;
                        break;
                    case "-":
                        // Subtraction
                        current_result = LHS - RHS;
                        break;
                    case "*":
                        // Multiplication
                        current_result = LHS * RHS;
                        break;
                    case "/":
                        // Division
                        current_result = LHS / RHS;
                        break;
                    default:
                        // We have some problem, Operand not found
                        return false;
                }
                LHS = current_result;

            }

        return true;
    }

    @Override
    public double getResult(){
        return current_result;
    }
}
