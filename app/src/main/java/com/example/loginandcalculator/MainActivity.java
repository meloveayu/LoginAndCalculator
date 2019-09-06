package com.example.loginandcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextUsername;
    private EditText mEditTextPassword;
    private Button mButtonLogin;
    final String username = "root";
    final String password = "1234";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditTextUsername = findViewById(R.id.username);
        mEditTextPassword = findViewById(R.id.password);
        mButtonLogin = findViewById(R.id.login);
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = mEditTextUsername.getText().toString().trim();
                String pwd = mEditTextPassword.getText().toString().trim();
                if(uid.isEmpty()){
                    Toast.makeText(MainActivity.this,R.string.err_un_empty,Toast.LENGTH_SHORT).show();
                }
                else if(!uid.equals(username)){
                    Toast.makeText(MainActivity.this,R.string.err_un_false,Toast.LENGTH_SHORT).show();
                }
                else {
                    if(pwd.isEmpty()){
                        Toast.makeText(MainActivity.this,R.string.err_pw_empty,Toast.LENGTH_SHORT).show();
                    }
                    else if(!pwd.equals(password)){
                        Toast.makeText(MainActivity.this,R.string.err_pw_false,Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this,R.string.getin,Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this,Main2Activity.class);
                        startActivity(i);
                    }
                }
            }
        });
    }
}
