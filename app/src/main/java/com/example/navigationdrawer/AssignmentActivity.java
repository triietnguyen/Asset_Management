package com.example.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AssignmentActivity extends AppCompatActivity {
    ImageView img_Back_Assignment;
    Button btn_New_Request;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        AnhXa();
        Handle_Component();
    }

    void AnhXa(){
        img_Back_Assignment = (ImageView)findViewById(R.id.img_Back_AssignmentPage);
        btn_New_Request = (Button)findViewById(R.id.btn_New_Request_AssignmentPage);
    }

    void Handle_Component(){
        img_Back_Assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_New_Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssignmentActivity.this, NewRequestActivity.class);
                startActivity(intent);
            }
        });
    }
}
