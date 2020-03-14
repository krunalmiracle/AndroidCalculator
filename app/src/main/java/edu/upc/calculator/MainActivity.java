package edu.upc.calculator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Vector;


public class MainActivity extends AppCompatActivity {

    private Vector<String> math_list = new Vector<String>();
    protected CalculatorInterface _calc;
    private String _text_appended = "";
    boolean wasLast_Press_Number = true;
    TextView calculator_display_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculator_display_text   = (TextView) findViewById(R.id.txt_display);
        this._calc = new Calculator();
        this.math_list.add("");
    }

    public void Append_Text(String Txt,boolean isCurrent_Press_Number) {

        if (wasLast_Press_Number && isCurrent_Press_Number){ //Is a number and was a number
            _text_appended = _text_appended +  Txt;
            calculator_display_text.setText(_text_appended);
            String tmp = math_list.lastElement()+Txt;
            math_list.setElementAt(tmp,math_list.indexOf(math_list.lastElement()));
            wasLast_Press_Number = true;
        }
        else if(wasLast_Press_Number && !isCurrent_Press_Number) //Is a operation
        {
            _text_appended = _text_appended +  Txt;
            calculator_display_text.setText(_text_appended);
            math_list.add(Txt);
            wasLast_Press_Number = false;
        }
        else if(!wasLast_Press_Number && isCurrent_Press_Number)//Is a number after a operation
        {
            _text_appended = _text_appended +  Txt;
            calculator_display_text.setText(_text_appended);
            math_list.add(Txt);
            wasLast_Press_Number = true;
        }
    }

    public void ClickEventMethodButton0(View view) {
        //_text_appended = edit_text.getText().toString()+_text_appended;
        Append_Text("0",true);
    }
    public void ClickEventMethodButtonEqual(View view) {
        //SOLVE THE EQUATION
        //Append_Text("Solution",false);
        _text_appended = "Error";
        if(wasLast_Press_Number){
            //Size is correct of the list a operand
            if(this._calc.Parse_List_To_Result(this.math_list))
            {
                double result = this._calc.getResult();
                this._text_appended = String.valueOf(result);
                calculator_display_text.setText(this._text_appended);
            }
            else {
                _text_appended = "ERROR";
                calculator_display_text.setText(_text_appended);
            }
        }
        else
        {   _text_appended = "ERROR";
            calculator_display_text.setText(_text_appended);
        }
        this.math_list.removeAllElements();
        this.math_list.add("");
        _text_appended = "";
        boolean wasLast_Press_Number = true;
    }
    public void ClickEventMethodButtonDecimal(View view) {
        //FOR FUTURE IMPLEMENTATION
    }


    public void ClickEventMethodButtonClear(View view) {
        calculator_display_text.setText("");
        _text_appended = "";
        boolean wasLast_Press_Number = false;
    }

    public void ClickEventMethodButtonSine(View view) {
        //FUTURE IMPLEMENTATION
    }

    public void ClickEventMethodButtonCosine(View view) {
        //FUTURE IMPLEMENTATION
    }

    public void ClickEventMethodButtonTangent(View view) {
        //FUTURE IMPLEMENTATION
    }

    public void ClickEventMethodButtonRAD_DEG(View view) {
        //FUTURE IMPLEMENTATION
    }

    public void ClickEventMethodButton7(View view) {
        Append_Text("7",true);
    }

    public void ClickEventMethodButton8(View view) {
        Append_Text("8",true);
    }

    public void ClickEventMethodButton9(View view) {
        Append_Text("9",true);
    }

    public void ClickEventMethodButtonMultiply(View view) {
        Append_Text("*",false);
    }

    public void ClickEventMethodButton4(View view) {
        Append_Text("4",true);
    }

    public void ClickEventMethodButton5(View view) {
        Append_Text("5",true);
    }

    public void ClickEventMethodButton6(View view) {
        Append_Text("6",true);
    }

    public void ClickEventMethodButtonSubtract(View view) {
        Append_Text("-",false);
    }

    public void ClickEventMethodButton1(View view) {
        Append_Text("1",true);
    }

    public void ClickEventMethodButton2(View view) {
        Append_Text("2",true);
    }

    public void ClickEventMethodButton3(View view) {
        Append_Text("3",true);
    }

    public void ClickEventMethodButtonAdd(View view) {
        Append_Text("+",false);
    }

    public void ClickEventMethodButtonDel(View view) {
        //FutureImplementation
    }
}
