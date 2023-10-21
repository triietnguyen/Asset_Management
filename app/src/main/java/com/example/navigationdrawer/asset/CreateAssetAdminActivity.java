package com.example.navigationdrawer.asset;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.main.MainAdminActivity;

import java.util.ArrayList;
import java.util.List;

import Models.Asset;

public class CreateAssetAdminActivity extends AppCompatActivity {
    AssetAdminApdapter assetAdminApdapter;
    Button img_Cancel_Create;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_asset_admin);
        AnhXa();
        Handle_Component();
    }
    void AnhXa(){

        img_Cancel_Create = (Button) findViewById(R.id.img_Cancel_Create);
    }
    void Handle_Component(){
        img_Cancel_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAssetAdminActivity.this, AssetAdminActivity.class);
                startActivity(intent);
            }
        });

    }
}
