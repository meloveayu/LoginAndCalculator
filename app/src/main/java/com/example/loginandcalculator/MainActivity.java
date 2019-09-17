package com.example.loginandcalculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private EditText mEditTextUsername;
    private EditText mEditTextPassword;
    private static final int REQUEST_CODE_FINISH = 0;
    private String question;
    private String mSentance = "Please Calculate ";
    private final String USERNAME = "root";
    private final String PASSWORD = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RandomEqu r = new RandomEqu();
        textViewQuestion = findViewById(R.id.text_question);
        mEditTextUsername = findViewById(R.id.username);
        mEditTextPassword = findViewById(R.id.password);
        Button buttonLogin = findViewById(R.id.login);

        double r1 = (double) r.randomR1();
        double r2 = (double) r.randomR2();
        char sign = r.randomSign();
        final double re = r.printRe(sign,r1,r2);

        question = String.format(getResources().getString(R.string.question),r1,sign,r2);
        textViewQuestion.setText(question);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = mEditTextUsername.getText().toString().trim();
                String pwd = mEditTextPassword.getText().toString().trim();
                if(uid.isEmpty()){
                    Toast.makeText(MainActivity.this,R.string.err_un_empty,Toast.LENGTH_SHORT).show();
                }
                else if(!uid.equals(USERNAME)){
                    Toast.makeText(MainActivity.this,R.string.err_un_false,Toast.LENGTH_SHORT).show();
                }
                else {
                    if(pwd.isEmpty()){
                        Toast.makeText(MainActivity.this,R.string.err_pw_empty,Toast.LENGTH_SHORT).show();
                    }
                    else if(!pwd.equals(PASSWORD)){
                        Toast.makeText(MainActivity.this,R.string.err_pw_false,Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this,R.string.getin,Toast.LENGTH_SHORT).show();
                        Intent i = Main2Activity.newIntent(MainActivity.this,question,re);
                        startActivityForResult(i,REQUEST_CODE_FINISH);
                    }
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK){
            return;
        }
        if (requestCode == REQUEST_CODE_FINISH){
            if (data == null){
                return;
            }
            mSentance += Main2Activity.wasAnswerShown(data);
            textViewQuestion.setText(mSentance);
        }
    }
}
