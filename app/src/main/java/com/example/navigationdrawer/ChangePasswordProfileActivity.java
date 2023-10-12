package com.example.navigationdrawer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.navigationdrawer.databinding.ActivityChangepassBinding;
import com.example.navigationdrawer.databinding.ActivityUserProfileBinding;

public class ChangePasswordProfileActivity extends AppCompatActivity {

    ImageView button;
    ImageButton img_Btn_Save;
    ChangePasswordProfile_ModelView changePasswordProfileModelView = new ChangePasswordProfile_ModelView();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChangepassBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_changepass);
        binding.setChangePasswordModelView(changePasswordProfileModelView);

        AnhXa();
        Handle_Component();

    }
    void AnhXa(){
        button = (ImageView)findViewById(R.id.back_activity);
        img_Btn_Save =(ImageButton)findViewById(R.id.img_btn_save);
    }
    void Handle_Component(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_Btn_Save.setOnClickListener(view -> changePasswordProfileModelView.OnClickButton(this));
    }
}
