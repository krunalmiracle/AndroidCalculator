package edu.upc.calculator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Vector;


public class MainActivity extends AppCompatActivity {

    private Vector<String> math_list = new Vector<String>();
    protected CalculatorInterface _calc;
    private String _text_appended = " ";
    boolean wasLast_Press_Number = false;
    String str_a = "";
    String str_op = "";
    int a = 1;
    TextView calculator_display_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculator_display_text   = (TextView) findViewById(R.id.txt_display);
        this._calc = new Calculator();
    }

    public void Append_Text(String Txt,boolean isCurrent_Press_Number) {

        if (wasLast_Press_Number || isCurrent_Press_Number){
            _text_appended = _text_appended +  Txt;
            calculator_display_text.setText(_text_appended);
                math_list.add(Txt);
            wasLast_Press_Number = isCurrent_Press_Number;
        }
    }

    public void ClickEventMethodButton0(View view) {
        //_text_appended = edit_text.getText().toString()+_text_appended;
        Append_Text("0",true);
    }
    public void ClickEventMethodButtonEqual(View view) {
        //SOLVE THE EQUATION
        //Append_Text("Solution",false);
        String text_local = math_list.toString() +" Test";
        calculator_display_text.setText(text_local);
        _text_appended = "";
        if(wasLast_Press_Number){
            //Size is correct of the list a operand b
            text_local = "Solution";
            calculator_display_text.setText(text_local);
            if(this._calc.Parse_list_to_operation(this.math_list))
            {
                double result = this._calc.getResult();
            }
            else {
                text_local = "Error";
                calculator_display_text.setText(text_local);
            }
        }
        else
        {
            text_local = "Error";
            calculator_display_text.setText(text_local);
        }
        math_list.removeAllElements();
        boolean wasLast_Press_Number = false;
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
