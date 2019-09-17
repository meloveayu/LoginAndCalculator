package com.example.loginandcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "com.example.loginandcalculator.question";
    private static final String TAG2 = "com.example.loginandcalculator.result";
    private static final String SHOWN = "com.example.loginandcalculator.shown";
    private TextView mTextViewEquals;
    private Button[] buttons = new Button[17];
    private int[] ids = new int[]{R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3,
            R.id.button_4,R.id.button_5,R.id.button_6,R.id.button_7,R.id.button_8,R.id.button_9,
            R.id.button_add,R.id.button_sub,R.id.button_mul,R.id.button_div,R.id.button_point,
            R.id.button_clr,R.id.button_submit};
    private double re;
    private String question;
    private String sentance = "";

    public static Intent newIntent(Context packageContext, String question,double result){
        Intent i = new Intent(packageContext,Main2Activity.class);
        i.putExtra(TAG,question).putExtra(TAG2,result);
        return i;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView textViewQuestion = findViewById(R.id.text_question_2);
        mTextViewEquals = findViewById(R.id.equals_text);
        re = getIntent().getDoubleExtra(TAG2,0);
        question = getIntent().getStringExtra(TAG);
        textViewQuestion.setText(question);
        //批量化
        for (int i = 0;i != buttons.length;i++){
            buttons[i] = findViewById(ids[i]);
            buttons[i].setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View view) {
        String str = mTextViewEquals.getText().toString();
        switch (view.getId()){
            default:
                break;
            case R.id.button_0:
            case R.id.button_1:
            case R.id.button_2:
            case R.id.button_3:
            case R.id.button_4:
            case R.id.button_5:
            case R.id.button_6:
            case R.id.button_7:
            case R.id.button_8:
            case R.id.button_9:
            case R.id.button_point:
                mTextViewEquals.setText(str +  ((Button)view).getText().toString());
                break;
            case R.id.button_add:
            case R.id.button_sub:
            case R.id.button_mul:
            case R.id.button_div:
                getOperator(str,view);
                break;
            case R.id.button_clr:
                mTextViewEquals.setText("");
                break;
            case R.id.button_submit:
                getResult(re);
                break;
        }
    }

    private void getOperator(String str, View view){
        mTextViewEquals.setText(str + " " + ((Button)view).getText().toString() + " ");
        //more
    }

    private void getResult(double re){
        String equ = mTextViewEquals.getText().toString();
        if(equ.isEmpty()){
            return;
        }
        if(!equ.contains(" ")){
            return;
        }
        //catch the sign at the beginning
        String s1 = equ.substring(0,equ.indexOf(" "));
        //sign for calculate
        String op = equ.substring(equ.indexOf(" ") + 1,equ.indexOf(" ") + 2);
        //get the str after the sign for calculate
        String s2 = equ.substring(equ.indexOf(" ") + 3);
        double result = 0;
        if (!s1.isEmpty() && !s2.isEmpty()){
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);

            if (op.equals("+")){
                result = d1 + d2;
            }
            else if (op.equals("-")){
                result = d1 - d2;
            }
            else if (op.equals("*")){
                result = d1 * d2;
            }
            else if (op.equals("/")){
                if (d2 == 0){
                    result = 0;
                }
                else {
                    result = d1 / d2;
                }
            }

            if (!s1.contains(".") && !s2.contains(".") && !op.equals("/")){
                int r = (int) result;
                int r2 = (int) re;
                if (r == r2){
                    Toast.makeText(this,R.string.right_text,Toast.LENGTH_SHORT).show();
                    sentance = equ + " = " + r;
                    mTextViewEquals.setText(sentance);
                    setAnswerShownFirstPage(sentance);
                }
                else {
                    Toast.makeText(this,R.string.wrong_text,Toast.LENGTH_SHORT).show();
                    mTextViewEquals.setText(equ + " = " + r);
                }

            }
            else {
                if (result == re){
                    Toast.makeText(this,R.string.right_text,Toast.LENGTH_SHORT).show();
                    sentance = equ + " = " + result;
                    mTextViewEquals.setText(sentance);
                    setAnswerShownFirstPage(sentance);
                }
                else {
                    Toast.makeText(this,R.string.wrong_text,Toast.LENGTH_SHORT).show();
                    mTextViewEquals.setText(equ + " = " + result);
                }
            }
        }
    }

    private void setAnswerShownFirstPage(String sen){
        Intent data = new Intent();
        data.putExtra(SHOWN,sen);
        setResult(RESULT_OK,data);
    }

    public static String wasAnswerShown(Intent result){
        return result.getStringExtra(SHOWN);
    }

}
