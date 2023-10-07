package com.example.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NewRequestActivity extends AppCompatActivity {

    ImageView img_back_assignment;
    EditText edt_date,edt_asset;
    Button btn_Save;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_request);
        AnhXa();
        Handle_Back_Component();
        Handle_Forgot_Component();
    }
    public void AnhXa(){
        img_back_assignment = (ImageView) findViewById(R.id.img_back_assignment);
        edt_date = (EditText)findViewById(R.id.edt_date);
        btn_Save = (Button)findViewById(R.id.btn_Save);
    }
    public void Handle_Back_Component(){
        img_back_assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewRequestActivity.this, AssignmentActivity.class);
                startActivity(intent);

            }
        });
    }
    public void Handle_Forgot_Component(){
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewRequestActivity.this, NewRequestActivity.class);
                startActivity(intent);
            }
        });
    }
}
