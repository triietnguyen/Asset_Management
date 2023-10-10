package com.example.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AssetAdminActivity extends AppCompatActivity {
    ImageView img_Back_Asset_Admin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_admin);
        AnhXa();
        Handle_Component();
    }

    void AnhXa(){
        img_Back_Asset_Admin = (ImageView) findViewById(R.id.back_Asset_AdminLayout);
    }
    void Handle_Component(){
        img_Back_Asset_Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssetAdminActivity.this, MainAdminActivity.class);
                startActivity(intent);
            }
        });

    }
}
