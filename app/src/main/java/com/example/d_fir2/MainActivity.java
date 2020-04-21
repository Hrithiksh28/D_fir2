package com.example.d_fir2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;


public class MainActivity extends Activity  {
    Button login, send_otp;
    EditText username,password;
    TextView register, tx1, timer;
    int counter = 3;
    int counter2 = 30;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        send_otp = findViewById(R.id.send_otp);

        tx1 = findViewById(R.id.attempt2);
        tx1.setVisibility(View.GONE);
        timer = findViewById(R.id.otptimer);

        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this,register.class);
                startActivity(i);
            }

        });
        send_otp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                new CountDownTimer(30000, 1000){
                    public void onTick(long millisUntilFinished){
                        counter2--;
                        timer.setText(String.valueOf(counter2));
                    }
                    @RequiresApi(api = Build.VERSION_CODES.Q)
                    public  void onFinish(){
                        timer.setText("0");
                        login.setEnabled(false);
                        send_otp.setEnabled(false);
                    }
                }.start();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("admin") &&
                        password.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Wrong Credentials",Toast.LENGTH_SHORT).show();

                    tx1.setVisibility(View.VISIBLE);
                    counter--;
                    tx1.setText(Integer.toString(counter));

                    if (counter == 0) {
                        login.setEnabled(false);
                    }
                }
            }
        });

    }

}