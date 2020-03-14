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
    public boolean Parse_list_to_operation(Vector<String> input){
        //Divide the list
            //Search for
            int first_element = 0;
            Vector<Integer> operands_index = new Vector<Integer>();

            for(int i=0;i<input.size();i++)
            {
                if(input.get(i).equals("+") || input.get(i).equals("-")|| input.get(i).equals("/")|| input.get(i).equals("*")) {
                    //It means we have a operation and all the elements to the left and right are number, until next operation
                    operands_index.add(i);
                }
            }
            if(operands_index.size()==0) //We only have number and so the result is the same
            {
                current_result = Double.parseDouble(input.lastElement());
                return true;
            }
            for(int k = 0 ;k<operands_index.size();k++) //Assuming we always have 1 operand and left/right hand side
            {

                double left_hand_side = Double.parseDouble(input.get(operands_index.get(k)-1));
                double right_hand_side = Double.parseDouble(input.get(operands_index.get(k)+1));
                //We can now do expression based math!!
                switch (input.get(operands_index.get(k)))
                {
                    case "+":
                        // Addition
                        current_result = left_hand_side + right_hand_side;
                        break;
                    case "-":
                        // Subtraction
                        current_result = left_hand_side - right_hand_side;
                        break;
                    case "*":
                        // Multiplication
                        current_result = left_hand_side * right_hand_side;
                        break;
                    case "/":
                        // Division
                        current_result = left_hand_side / right_hand_side;
                        break;
                    default:
                        // We have some problem, Operand not found
                        return false;
                }
            }

        return true;
    }

    @Override
    public double getResult(){
        return current_result;
    }
}
