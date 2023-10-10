package com.example.navigationdrawer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.navigationdrawer.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    ImageView imgView_Back;
    Button btn_Change_Password;
    ProfileActivity_ModelView profileActivityModelView = new ProfileActivity_ModelView();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProfileBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_profile);
        profileActivityModelView.GetData();
        binding.setProfileActivityModelView(profileActivityModelView);

        AnhXa();
        Handle_Component();
    }

    void AnhXa(){
        imgView_Back = (ImageView)findViewById(R.id.back_profile);
        btn_Change_Password = (Button)findViewById(R.id.changepass);
    }

    void Handle_Component(){
        imgView_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_Change_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ChangePasswordProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
