package com.example.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AssetActivity extends AppCompatActivity {
    ImageView img_Back_Asset;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset);
        AnhXa();
        Handle_Component();
    }

    void AnhXa(){
        img_Back_Asset = (ImageView) findViewById(R.id.back_AssetLayout);
    }

    void Handle_Component(){
        img_Back_Asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
