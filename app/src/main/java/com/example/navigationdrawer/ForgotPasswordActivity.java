package com.example.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    ImageView img_Back;
    Button btn_Submit;
    ImageButton imgbtn_Send;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);
        AnhXa();
        HandleComponent();
    }

    void AnhXa(){
        img_Back = (ImageView)findViewById(R.id.back_account);
        btn_Submit = (Button)findViewById(R.id.btn_Submit_ForgotPass_Layout);
        imgbtn_Send =(ImageButton)findViewById(R.id.img_btn_forgot_layout);
    }

    void HandleComponent(){
        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this, ChangPasswordForgetActivity.class);
                startActivity(intent);
                finish();
            }
        });
        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imgbtn_Send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgbtn_Send.setEnabled(false);
                    Handler();
                }
            });
    }
    void Handler(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgbtn_Send.setEnabled(true);
            }
        }, 10000);
    }
}
