package com.example.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NewRequestActivity extends AppCompatActivity {

    ImageView img_Back_NewRequest;
    EditText edt_Date, edt_Asset;
    Button btn_Save;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_request);
        AnhXa();
        Handle_Component();
    }
    public void AnhXa(){
        img_Back_NewRequest = (ImageView) findViewById(R.id.img_Back_NewRequestPage);
        edt_Date = (EditText)findViewById(R.id.edt_Date_NewRequestPage);
        btn_Save = (Button)findViewById(R.id.btn_Save_NewRequestPage);
    }
    public void Handle_Component(){
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewRequestActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        img_Back_NewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
