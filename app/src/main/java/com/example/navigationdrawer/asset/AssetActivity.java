package com.example.navigationdrawer.asset;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawer.main.MainActivity;
import com.example.navigationdrawer.R;

import java.util.ArrayList;
import java.util.List;

import Models.Asset;

public class AssetActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AssetApdapter assetApdapter;
    ImageView img_Back_Asset;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset);
        AnhXa();
        Handle_Component();
        setRecycleView();
    }

    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assetApdapter = new AssetApdapter(this, getList());
        recyclerView.setAdapter(assetApdapter);
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
        img_Back_Asset = (ImageView) findViewById(R.id.back_AssetLayout);
        recyclerView = findViewById(R.id.recycler_view_asset_layout);
    }

    void Handle_Component(){
        img_Back_Asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssetActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
