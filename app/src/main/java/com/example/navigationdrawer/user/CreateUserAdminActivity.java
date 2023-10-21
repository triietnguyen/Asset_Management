package com.example.navigationdrawer.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.asset.AssetAdminActivity;
import com.example.navigationdrawer.asset.AssetAdminApdapter;

public class CreateUserAdminActivity extends AppCompatActivity {
    Button img_Cancel_Create;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_staff_admin);
        AnhXa();
        Handle_Component();
    }
    void AnhXa(){

        img_Cancel_Create = (Button) findViewById(R.id.img_Cancel_Layout);
    }
    void Handle_Component(){
        img_Cancel_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateUserAdminActivity.this, UserAdminActivity.class);
                startActivity(intent);
            }
        });

    }
}
