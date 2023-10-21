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

import com.example.navigationdrawer.main.MainAdminActivity;
import com.example.navigationdrawer.R;

import java.util.ArrayList;
import java.util.List;

import Models.Asset;

public class AssetAdminActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AssetAdminApdapter assetAdminApdapter;

    Button btn_create;

    ImageView back_Asset_AdminLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_admin);
        AnhXa();
        Handle_Component();
        setRecycleView();
    }

    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assetAdminApdapter = new AssetAdminApdapter(this, getList());
        recyclerView.setAdapter(assetAdminApdapter);
    }

    private List<Asset> getList() {
        List<Asset> assetList = new ArrayList<>();
        assetList.add(new Asset("a","a","a","1"));
        assetList.add(new Asset("a","a","a","1"));
        assetList.add(new Asset("a","a","a","1"));
        assetList.add(new Asset("a","a","a","1"));
        assetList.add(new Asset("a","a","a","1"));
        assetList.add(new Asset("a","a","a","1"));
        assetList.add(new Asset("a","a","a","1"));
        assetList.add(new Asset("a","a","a","1"));
        assetList.add(new Asset("a","a","a","1"));
        assetList.add(new Asset("a","a","a","1"));
        assetList.add(new Asset("a","a","a","1"));
        assetList.add(new Asset("a","a","a","1"));
        assetList.add(new Asset("a","a","a","1"));
        assetList.add(new Asset("a","a","a","1"));
        assetList.add(new Asset("a","a","a","1"));
        assetList.add(new Asset("a","a","a","1"));
        assetList.add(new Asset("a","a","a","1"));
        return  assetList;
    }

    void AnhXa(){
        btn_create = (Button) findViewById(R.id.btn_create);
        back_Asset_AdminLayout = (ImageView) findViewById(R.id.back_Asset_AdminLayout);
        recyclerView = findViewById(R.id.recycler_view_asset_layout_admin);
    }
    void Handle_Component(){
        back_Asset_AdminLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssetAdminActivity.this, MainAdminActivity.class);
                startActivity(intent);
            }
        });
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssetAdminActivity.this, CreateAssetAdminActivity.class);
                startActivity(intent);
            }
        });
    }
}
