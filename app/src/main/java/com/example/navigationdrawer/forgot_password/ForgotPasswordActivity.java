package com.example.navigationdrawer.forgot_password;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.navigationdrawer.BR;
import com.example.navigationdrawer.R;
import com.example.navigationdrawer.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import ViewModels.ForgotPassword_ModelView;
//import kotlinx.coroutines.channels.Send;

public class ForgotPasswordActivity extends AppCompatActivity {

    ImageView img_Back;
    Button btn_Submit;
    ImageButton imgbtn_Send;
    EditText edt;
    private List<Integer> listNumberRandom = new ArrayList<Integer>();
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
        btn_Submit = (Button)findViewById(R.id.btn_Submit_ForgotPass_Layout);
        imgbtn_Send =(ImageButton)findViewById(R.id.img_btn_forgot_layout);
        edt = (EditText) findViewById(R.id.edt_email_forgot);
    }

    void HandleComponent(){
        btn_Submit.setOnClickListener(view -> forgotPasswordModelView.OnClickButtonOTP(this));

        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        imgbtn_Send.setOnClickListener(view -> forgotPasswordModelView.OnClickButtonEmail(this));

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
