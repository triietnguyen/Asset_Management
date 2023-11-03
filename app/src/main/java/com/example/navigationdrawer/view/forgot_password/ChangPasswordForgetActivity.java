package com.example.navigationdrawer.view.forgot_password;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.navigationdrawer.BR;
import com.example.navigationdrawer.R;

import com.example.navigationdrawer.viewmodel.ChangePasswordForget_ModelView;

public class ChangPasswordForgetActivity extends AppCompatActivity {
    ImageView img_Back;
    ImageView img_Save;
    private ChangePasswordForget_ModelView changePasswordForget_ModelView = new ChangePasswordForget_ModelView();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewDataBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_changepass_forget);
        activityMainBinding.setVariable(BR.ChangePasswordForget_ModelView, changePasswordForget_ModelView);

        AnhXa();

        Handle_Component();

    }


    void AnhXa(){
        img_Back = (ImageView)findViewById(R.id.back);
        img_Save = (ImageView)findViewById(R.id.img_Save);
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
