package com.example.navigationdrawer.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.navigationdrawer.BR;


import com.example.navigationdrawer.databinding.ActivityLoginBinding;
import com.example.navigationdrawer.databinding.ActivityMainBinding;
import com.example.navigationdrawer.forgot_password.ForgotPasswordActivity;
import com.example.navigationdrawer.R;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ViewModels.Login_ModelView;


public class LoginActivity extends AppCompatActivity {
    EditText edt_Email,edt_Password;
    Button btn_SignIn;
    TextView txt_Forgot;

    private Login_ModelView loginModelView = new Login_ModelView();;
    Connection connect;
    String ConnectionResult = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ViewDataBinding activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        activityLoginBinding.setVariable(BR.Login_ModelView,loginModelView);


        ViewDataBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityMainBinding.setVariable(BR.Login_ModelView, loginModelView);
        AnhXa();
        Handle_Component();
    }
    public void AnhXa(){
        edt_Email = (EditText)findViewById(R.id.edt_email_LoginPage);
        edt_Password = (EditText)findViewById(R.id.edt_Password_LoginPage);
        txt_Forgot =(TextView)findViewById(R.id.txt_ForgotPassword_LoginPage);
        btn_SignIn = (Button)findViewById(R.id.btn_SignIn_LoginPage);
    }
    public void Handle_Component(){
        txt_Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        btn_SignIn.setOnClickListener(view -> loginModelView.OnClickButton(this));
    }



    public boolean OnValid_OTP(List<Integer> numberRandom, String numberOfUser){
        String str_numberRandom = "";
        for(int i=0;i<numberRandom.size();i++){
            str_numberRandom = str_numberRandom + numberRandom.get(i);
        }
        if(str_numberRandom.trim().equalsIgnoreCase(numberOfUser.trim())){
            Log.e("123","Correct");
            return true;
        }
        Log.e("123","User : "+numberOfUser.trim());
        Log.e("123","Random : "+str_numberRandom.trim());
        Log.e("123","InCorrect");
        return false;
    }

    public List<Integer> HandleOtp_SMS(){
        List<Integer> numberRandom = new ArrayList<Integer>();
        Random random = new Random();
        int idxRandom ;
        for(int i=0;i<6;i++){
            idxRandom = random.nextInt(10);
            numberRandom.add((idxRandom));
        }
        return numberRandom;
    }

}

