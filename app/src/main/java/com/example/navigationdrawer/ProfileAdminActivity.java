package com.example.navigationdrawer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileAdminActivity extends AppCompatActivity {
    ImageView imgView_Back;
    Button btn_Change_Password;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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
                Intent intent = new Intent(ProfileAdminActivity.this, MainAdminActivity.class);
                startActivity(intent);
            }
        });

        btn_Change_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileAdminActivity.this, ChangePasswordProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
