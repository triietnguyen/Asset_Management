package com.example.navigationdrawer;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import Models.MyApplication;

public class ChangPasswordForgetActivity extends AppCompatActivity {
    ImageView img_Back;
    ImageButton img_Save;
    private ChangePasswordForget_ModelView changePasswordForget_ModelView = new ChangePasswordForget_ModelView();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_changepass_forget);
        activityMainBinding.setVariable(BR.ChangePasswordForget_ModelView, changePasswordForget_ModelView);
        AnhXa();
        Handle_Component();
        MyApplication myApp = (MyApplication) getApplication();

        // Set và lấy dữ liệu
        String data = myApp.GetSharedData();
        Toast.makeText(this, "DATA : "+data, Toast.LENGTH_SHORT).show();
    }

    void AnhXa(){
        img_Back = (ImageView)findViewById(R.id.back);
        img_Save = (ImageButton)findViewById(R.id.img_Save);
    }

    void Handle_Component(){
        img_Save.setOnClickListener(view -> changePasswordForget_ModelView.OnClickButton(this));
        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangPasswordForgetActivity.this,ForgotPasswordActivity.class );
                startActivity(intent);
                finish();
            }
        });

    }
}
