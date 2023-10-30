package com.example.navigationdrawer.profile;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.databinding.ActivityChangepassBinding;

import ViewModels.ChangePasswordProfile_ModelView;


public class ChangePasswordProfileAdminActivity extends AppCompatActivity {

    ImageView button;
    ChangePasswordProfile_ModelView changePasswordProfileModelView = new ChangePasswordProfile_ModelView();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChangepassBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_changepass);
        binding.setChangePasswordModelView(changePasswordProfileModelView);

        AnhXa();
        Handle_Component();
    }
    void AnhXa(){
        button = (ImageView)findViewById(R.id.back_activity);
    }
    void Handle_Component(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
