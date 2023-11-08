package com.example.navigationdrawer.view.request.edit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.view.assignment.AssignmentAdminActivity;
import com.example.navigationdrawer.view.request.RequestAdminActivity;

public class EditRequestAdminActivity extends AppCompatActivity {
    Button img_CancelPage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_request_admin);
        img_CancelPage = findViewById(R.id.img_CancelPage);
        img_CancelPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditRequestAdminActivity.this, RequestAdminActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
