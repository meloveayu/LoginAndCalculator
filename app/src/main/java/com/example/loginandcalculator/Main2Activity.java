package com.example.loginandcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    private TextView mTextViewEquals;
    private Button[] buttons = new Button[17];
    private int[] ids = new int[]{R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3,
            R.id.button_4,R.id.button_5,R.id.button_6,R.id.button_7,R.id.button_8,R.id.button_9,
            R.id.button_add,R.id.button_sub,R.id.button_mul,R.id.button_div,R.id.button_point,
            R.id.button_clr,R.id.button_submit};
    private boolean end = false;
    private String expression = "";
    private int countoperate = 2;//限制符号的多次输入

    private double count(){
        double result = 0;//结果
        double tNum = 1,lowNum = 0.1,num = 0;//？
        char tmp ;
        int operate = 1;
        boolean point = false;
        for (int i = 0; i < expression.length();i++){
            tmp = expression.charAt(i);
            if (tmp == '.'){
                point = true;
                lowNum = 0.1;
            }
            else if (tmp == '+' ||tmp == '-'){
                if (operate != 3 && operate != -3){
                    tNum *= num;
                }
                else {
                    tNum /= num;
                }
                if (operate < 0){
                    result -= tNum;
                }
                else {
                    result += tNum;
                }
                if (tmp == '+'){
                    operate = 1;
                }
                else {
                    operate = -1;
                }
                num = 0;
                tNum = 1;
                point = false;
            }
            else if (tmp == '*'){
                if (operate != 3 && operate != -3){
                    tNum *= num;
                }
                else {
                    tNum /= num;
                }
                if (operate < 0){
                    operate = -2;
                }
                else {
                    operate = 2;
                }
                point = false;
                num = 0;
            }
            else if (tmp == '/'){
                if (operate != 3 &&operate != -3){
                    tNum *= num;
                }
                else {
                    tNum /= num;
                }
                if (operate < 0){
                    operate = -3;
                }
                else {
                    operate = 3;
                }
                point = false;
                num = 0;
            }
            else {
                if (!point){
                    num = num * 10 + tmp - '0';
                }
                else {
                    num += (tmp - '0') * lowNum;
                    lowNum *= 0.1;
                }
            }

        }
        if (operate != 3 && operate != -3){
            tNum *= num;
        }
        else {
            tNum /= num;
        }
        if (operate < 0){
            result -= tNum;
        }
        else {
            result += tNum;
        }
        return result;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mTextViewEquals = findViewById(R.id.equals_text);
        //批量化
        for (int i = 0;i != buttons.length;i++){
            buttons[i] = findViewById(ids[i]);
            buttons[i].setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Button button = view.findViewById(id);
        String current = button.getText().toString();
        //若end为true，则说明点击过了SUBMIT，再输入就要清空了
        if(end){
            expression = "";
            end = false;
        }
        //点击了C键，清空了
        if (current.equals("C")){
            expression = "";
            countoperate = 2;
        }
        else if (current.equals(".")){
            if(expression.equals("")||countoperate == 2){
                expression += "0.";
                countoperate = 1;//？
            }
            if (countoperate == 0){
                expression += ".";
                countoperate = 1;
            }
        }
        else if (current.equals("+") || current.equals("-") || current.equals("*") ||
                current.equals("/")){
            if (countoperate == 0){
                expression += current;
                countoperate = 2;
            }
        }
        else if (current.equals("SUBMIT")){
            double result = (double) Math.round(count() * 100) / 100;
            expression += "=" + result;
            end = true;
        }
        else {
            expression += current;
            if (countoperate == 2 || countoperate == 1){
                countoperate = 0;
            }
        }
        mTextViewEquals.setText(expression);
    }
}
