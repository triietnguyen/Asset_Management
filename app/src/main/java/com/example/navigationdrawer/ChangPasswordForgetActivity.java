package com.example.navigationdrawer;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChangPasswordForgetActivity extends AppCompatActivity {
    ImageView img_Back;
    ImageButton img_Save;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass_forget);
        AnhXa();
        Handle_Component();
    }

    void AnhXa(){
        img_Back = (ImageView)findViewById(R.id.back);
        img_Save = (ImageButton)findViewById(R.id.img_Save);
    }

    void Handle_Component(){
        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangPasswordForgetActivity.this,ForgotPasswordActivity.class );
                startActivity(intent);
                finish();
            }
        });

        img_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChangPasswordForgetActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
