package com.example.navigationdrawer.asset;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawer.assignment.AssignmentAdminActivity;
import com.example.navigationdrawer.login.LoginActivity;
import com.example.navigationdrawer.main.MainAdminActivity;
import com.example.navigationdrawer.R;
import com.example.navigationdrawer.profile.ProfileAdminActivity;
import com.example.navigationdrawer.report.ReportActivity;
import com.example.navigationdrawer.request.RequestAdminActivity;
import com.example.navigationdrawer.user.UserAdminActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import Models.Asset;
import ViewModels.Admin.AssetAdminActivity_ModelView;
import ViewModels.Admin.UserAdminActivity_ModelView;

public class AssetAdminActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    RecyclerView recyclerView;
    AssetAdminApdapter assetAdminApdapter;

    AssetAdminActivity_ModelView assetAdminActivityModelView;

    Button btn_create;

    ImageView imageMenu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_admin);
        AnhXa();
        Handle_Component();
        setRecycleView();
    }

    private void setRecycleView() {
        assetAdminActivityModelView = new AssetAdminActivity_ModelView();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assetAdminApdapter = new AssetAdminApdapter(this, assetAdminActivityModelView.GetAllAsset());
        recyclerView.setAdapter(assetAdminApdapter);
    }

    void AnhXa(){
        btn_create = (Button) findViewById(R.id.btn_create);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);
        imageMenu = findViewById(R.id.imageMenu);
        recyclerView = findViewById(R.id.recycler_view_asset_layout_admin);
    }
    void Handle_Component(){
        // Navigation Drawer------------------------------
        toggle = new ActionBarDrawerToggle(AssetAdminActivity.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Drawer click event
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.mHome) {
                    Intent intent = new Intent(AssetAdminActivity.this, MainAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mAsset) {
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mAssignment) {
                    Intent intent = new Intent(AssetAdminActivity.this, AssignmentAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mRequest) {
                    Intent intent = new Intent(AssetAdminActivity.this, RequestAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mProfile) {
                    Intent intent = new Intent(AssetAdminActivity.this, ProfileAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mStaff) {
                    Intent intent = new Intent(AssetAdminActivity.this, UserAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mFeedback) {
                    Toast.makeText(AssetAdminActivity.this, "Feedback", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mReport) {
                    Intent intent = new Intent(AssetAdminActivity.this, ReportActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mLogout) {
                    Intent intent = new Intent(AssetAdminActivity.this, LoginActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                    finish();
                }
                return false;
            }
        });

        // App Bar Click Event
        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssetAdminActivity.this, CreateAssetAdminActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
