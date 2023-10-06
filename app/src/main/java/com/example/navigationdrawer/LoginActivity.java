package com.example.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Models.Account;
import Models.JavaMailAPI;


public class LoginActivity extends AppCompatActivity {
    EditText edt_Email,edt_Password;
    Button btn_SignIn;
    TextView txt_Forgot;

    private List<Integer> listNumberRandom = new ArrayList<Integer>();
    Connection connect;
    String ConnectionResult = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        Handle_Sign_In_Component();
        Handle_Forgot_Component();
    }
    public void AnhXa(){
        edt_Email = (EditText)findViewById(R.id.edt_email_LoginPage);
        edt_Password = (EditText)findViewById(R.id.edt_Pasword_LoginPage);
        txt_Forgot =(TextView)findViewById(R.id.TxtFgPass);
        btn_SignIn = (Button)findViewById(R.id.btn_SignIn_LoginPage);
    }
    public void Handle_Sign_In_Component(){
        btn_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//                Toast.makeText(LoginActivity.this, "Login User Page", Toast.LENGTH_SHORT).show();
//                SendMail();
                OnCheckValidAccount();
            }
        });
    }
    public void Handle_Forgot_Component(){
        txt_Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPass.class);
                startActivity(intent);
            }
        });
    }


    public void OnCheckValidAccount(){
        Account account = new Account();

        int roleAdmin = 1;
        int roleUser = 2;

        boolean isValidAccount = account.IsCheckValidAccount(
                edt_Email.getText().toString().trim(),
                edt_Password.getText().toString().trim());
        int roleId = account.GetRoleId();
        if(isValidAccount && roleId == roleUser){

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Login User Page", Toast.LENGTH_SHORT).show();

        }
        else if(isValidAccount && roleId == roleAdmin){

            Toast.makeText(this, "Login Admin Page", Toast.LENGTH_SHORT).show();

        }
        else Toast.makeText(this, "Đăng nhập sai", Toast.LENGTH_SHORT).show();


    }

    public void HandleForgotPassword(){
        ///                                                    buttonForgotPassword.gettext().tostring(); cai nay la cua user nguoi dung nhap vao
        boolean isValid_SMS = OnValid_OTP(listNumberRandom,"123456");
    }

    public void SendMail(){
        listNumberRandom.clear();
        listNumberRandom = HandleOtp_SMS();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listNumberRandom.clear();
                Log.e("Da xoa",listNumberRandom.toString());
            }
        }, 30000);
        Log.e("test","test1");
        OnValid_OTP(listNumberRandom,"123456");
        Log.e("test","test2");
        JavaMailAPI java = new JavaMailAPI(this,edt_Email.getText().toString().trim(),listNumberRandom.toString());
        java.execute();
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

