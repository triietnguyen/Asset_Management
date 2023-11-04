package com.example.navigationdrawer.view.forgot_password;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.navigationdrawer.BR;
import com.example.navigationdrawer.R;
import com.example.navigationdrawer.view.login.LoginActivity;

import com.example.navigationdrawer.viewmodel.ForgotPassword_ModelView;
//import kotlinx.coroutines.channels.Send;

public class ForgotPasswordActivity extends AppCompatActivity {

    ImageView img_Back;
    ImageButton imgbtn_Send;
    private ForgotPassword_ModelView forgotPasswordModelView = new ForgotPassword_ModelView();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_forgotpass);
        activityMainBinding.setVariable(BR.ForgotPassword, forgotPasswordModelView);
        AnhXa();
        HandleComponent();
    }
    void AnhXa(){
        img_Back = (ImageView)findViewById(R.id.back_account);
        imgbtn_Send =(ImageButton)findViewById(R.id.img_btn_forgot_layout);
    }

    void HandleComponent(){
        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
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
