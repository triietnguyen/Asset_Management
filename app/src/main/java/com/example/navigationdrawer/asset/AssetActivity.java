package com.example.navigationdrawer.asset;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawer.assignment.AssignmentActivity;
import com.example.navigationdrawer.login.LoginActivity;
import com.example.navigationdrawer.main.MainActivity;
import com.example.navigationdrawer.R;
import com.example.navigationdrawer.profile.ProfileActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import Models.Asset;

public class AssetActivity extends AppCompatActivity {
    DrawerLayout drawer_Layout;
    NavigationView navigation_View;
    ActionBarDrawerToggle action_Toggle;
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
        drawer_Layout = findViewById(R.id.drawer_layout);
        navigation_View = findViewById(R.id.nav_View);
        img_Back_Asset = findViewById(R.id.back_AssetLayout);
        recyclerView = findViewById(R.id.recycler_view_asset_layout);
    }

    void Handle_Component(){
        // Navigation Drawer------------------------------
        action_Toggle = new ActionBarDrawerToggle(AssetActivity.this, drawer_Layout, R.string.open, R.string.close);
        drawer_Layout.addDrawerListener(action_Toggle);
        action_Toggle.syncState();


        // Drawer click event
        navigation_View.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.mHome) {
                    Intent intent = new Intent(AssetActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mAsset) {
                    Intent intent = new Intent(AssetActivity.this, AssetActivity.class);
                    startActivity(intent);
                    finish();
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mAssignment) {
                    Intent intent = new Intent(AssetActivity.this, AssignmentActivity.class);
                    startActivity(intent);
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mProfile) {
                    Intent intent = new Intent(AssetActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    finish();
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mLogout) {
                    Intent intent = new Intent(AssetActivity.this, LoginActivity.class);
                    startActivity(intent);
                    drawer_Layout.closeDrawers();
                    finish();
                }
                return false;
            }
        });

        // App Bar Click Event
        img_Back_Asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_Layout.openDrawer(GravityCompat.START);
            }
        });
    }
}
