package com.example.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AssignmentActivity extends AppCompatActivity {
    ImageView img_Back;
    Button btn_New_Request;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        AnhXa();
        Handle_Component();
    }

    void AnhXa(){
        img_Back = (ImageView)findViewById(R.id.img_back_assignment);
        btn_New_Request = (Button)findViewById(R.id.btn_New_Request);
    }

    void Handle_Component(){
        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssignmentActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_New_Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssignmentActivity.this, NewRequestActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
