package edu.upc.calculator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Vector;


public class MainActivity extends AppCompatActivity {

    Vector<ExpressionInterface> _expressions_vector = new Vector<ExpressionInterface>();
    protected CalculatorInterface _calc;
    TextView _calculator_display;

    private String _text_Clause = "";
    private boolean isRad = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _calculator_display = (TextView) findViewById(R.id.txt_display);
        this._calc = new Calculator();

    }

    //Adds an expression to vector of Expressions
    private void AddExpression(Object Value, ExpressionLevel expressionLevel) {
        //Expression<Double> expression_tmp = new Expression<Double>(22.2,ExpressionLevel.Primary);
        //Only Add Expression if mathematically correct else discard
        if(_expressions_vector.size()==0) //If First Place
        {
            if (expressionLevel == ExpressionLevel.Nullary || expressionLevel == ExpressionLevel.Primary ||expressionLevel == ExpressionLevel.Tertiary) {
                _expressions_vector.add(_expressions_vector.size(), new Expression<Object>(Value, expressionLevel));
            }
        }
        else
        {
            if (expressionLevel == ExpressionLevel.Nullary)
            {
                _expressions_vector.add(_expressions_vector.size(), new Expression<Object>(Value, expressionLevel));
            }
            else if (expressionLevel == ExpressionLevel.Primary) //+ & - Operand
            {
                if (_expressions_vector.get(_expressions_vector.size() - 1).get_ExpressionLevel() != ExpressionLevel.Secondary && _expressions_vector.get(_expressions_vector.size() - 1).get_ExpressionLevel() != ExpressionLevel.Primary )//Cannot go after multi or Division
                {
                    _expressions_vector.add(_expressions_vector.size(), new Expression<Object>(Value, expressionLevel));
                }
            }
            else if (expressionLevel == ExpressionLevel.Secondary) // * & / Operand
            {   //Cannot go after Primary(+&-) or Tertiary(Trig Funcs) or Secondary
                if (_expressions_vector.lastElement().get_ExpressionLevel() != ExpressionLevel.Primary && _expressions_vector.lastElement().get_ExpressionLevel() != ExpressionLevel.Tertiary &&_expressions_vector.lastElement().get_ExpressionLevel() != ExpressionLevel.Secondary)
                {
                    _expressions_vector.add(_expressions_vector.size(), new Expression<Object>(Value, expressionLevel));
                }
            } else if (expressionLevel == ExpressionLevel.Tertiary) //Trig Operands
            {   //Cannot go after Nullary (Numbers)
                if (_expressions_vector.lastElement().get_ExpressionLevel() != ExpressionLevel.Nullary)
                {
                    _expressions_vector.add(_expressions_vector.size(), new Expression<Object>(Value, expressionLevel));
                }
            }
        }
    }

    private void AppendDigit(String digit){ //Appending Numeric Digit to a Expression or Creating a new Expression

        if (_expressions_vector.size()>0)
        {
            if(_expressions_vector.lastElement().get_ExpressionLevel()==ExpressionLevel.Nullary)
            {
                Double tmp_Double = (Double) _expressions_vector.lastElement().get_Value();
                DecimalFormat format = new DecimalFormat("0.#");
                String tmp_str = String.format ("%.0f", tmp_Double);
                tmp_str = tmp_str + digit;
                _expressions_vector.setElementAt( new Expression<Double>( Double.parseDouble(tmp_str), ExpressionLevel.Nullary),_expressions_vector.size()-1);
            }
            else //It means we have a preceding expression which is not a Number
            {
                AddExpression(Double.parseDouble(digit), ExpressionLevel.Nullary);
            }

        }
        else //It means we have a number without any preceding Operator or Number & also first Expression
        {
            AddExpression(Double.parseDouble(digit), ExpressionLevel.Nullary);
        }
        UpdateCalculatorDisplay();
    }
    private void UpdateCalculatorDisplay()
    {   _text_Clause = ""; //_expressions_vector.get(i).get_Value()
        String tmp_str = "";
        for(int i =0;i<this._expressions_vector.size();i++)
    {       if(this._expressions_vector.get(i).get_ExpressionLevel().equals(ExpressionLevel.Nullary))
            {
                Double tmp_Double = (Double) this._expressions_vector.get(i).get_Value();
                DecimalFormat format = new DecimalFormat("0.#");
                tmp_str = String.format("%.0f", tmp_Double);
            }
            else
            {
                tmp_str = this._expressions_vector.get(i).get_Value().toString();
            }
                _text_Clause = _text_Clause + tmp_str;
        }
        this._calculator_display.setText(_text_Clause);
    }

    public void ClickEventMethodButtonSine(View view) {
        AddExpression("Sin",ExpressionLevel.Tertiary);
        UpdateCalculatorDisplay();
    }

    public void ClickEventMethodButtonCosine(View view) {
        AddExpression("Cos",ExpressionLevel.Tertiary);
        UpdateCalculatorDisplay();
    }

    public void ClickEventMethodButtonTangent(View view) {
        AddExpression("Tan",ExpressionLevel.Tertiary);
        UpdateCalculatorDisplay();
    }

    public void ClickEventMethodButtonRAD_DEG(View view) {
        //TOGGLES THE RAD TO DEGREE AND VICEVERSA
        this.isRad = !isRad;
        String tmp = "RAD";
        Button buttonRAD_DEG = findViewById(R.id.button_rad_deg);
        if(isRad) {buttonRAD_DEG.setBackgroundColor(Color.CYAN);buttonRAD_DEG.setText(tmp);}
        else{buttonRAD_DEG.setBackgroundColor(Color.YELLOW);tmp="DEG";buttonRAD_DEG.setText(tmp);}
    }


    //EXTRA FUNCTION BUTTONS
    public void ClickEventMethodButtonDel(View view) {
        if(this._expressions_vector.size()!=0) {
            this._expressions_vector.remove(this._expressions_vector.size() - 1);
            UpdateCalculatorDisplay();
        }
    }
    public void ClickEventMethodButtonClear(View view) {
        this._expressions_vector.removeAllElements();
        UpdateCalculatorDisplay();
    }
    public void ClickEventMethodButtonEqual(View view) {
        //SOLVE THE ExpressionVector
        if(_calc.ExpressionVector_To_Result(this._expressions_vector,this.isRad)){
            _text_Clause = String.valueOf(this._calc.get_Result());
        }
        else{
            _text_Clause = "Error";
        }
        this._calculator_display.setText(_text_Clause);
    }
    //OPERATIONS BUTTONS
    public void ClickEventMethodButtonAdd(View view) {
        //PRIMARY EXPRESSION
        AddExpression("+",ExpressionLevel.Primary);
        UpdateCalculatorDisplay();
    }
    public void ClickEventMethodButtonSubtract(View view) {
        AddExpression("-",ExpressionLevel.Primary);
        UpdateCalculatorDisplay();
    }
    public void ClickEventMethodButtonMultiply(View view) {
        //SECONDARY OPERATION
        AddExpression("*",ExpressionLevel.Secondary);
        UpdateCalculatorDisplay();
    }
    public void ClickEventMethodButtonDivide(View view) {
        AddExpression("/",ExpressionLevel.Secondary);
        UpdateCalculatorDisplay();
    }
    //NUMBER BUTTONS
    public void ClickEventMethodButton0(View view) {
        //Primary Expression
        AppendDigit("0");
    }
    public void ClickEventMethodButton1(View view) {
        //Primary Expression
        AppendDigit("1");
    }
    public void ClickEventMethodButton2(View view) {
        //Primary Expression
        AppendDigit("2");
    }
    public void ClickEventMethodButton3(View view) {
        //Primary Expression
        AppendDigit("3");
    }
    public void ClickEventMethodButton4(View view) {
        //Primary Expression
        AppendDigit("4");
    }
    public void ClickEventMethodButton5(View view) {
        //Primary Expression
        AppendDigit("5");
    }
    public void ClickEventMethodButton6(View view) {
        //Primary Expression
        AppendDigit("6");
    }
    public void ClickEventMethodButton7(View view) {
        //Primary Expression
        AppendDigit("7");
    }
    public void ClickEventMethodButton8(View view) {
        //Primary Expression
        AppendDigit("8");
    }
    public void ClickEventMethodButton9(View view) {
        //Primary Expression
        AppendDigit("9");
    }


}
