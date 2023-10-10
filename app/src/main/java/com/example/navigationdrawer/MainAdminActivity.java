package com.example.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.navigationdrawer.databinding.ActivityMainAdminBinding;
import com.example.navigationdrawer.databinding.DrawarAdminHeadLayoutBinding;
//import com.example.navigationdrawer.databinding.DrawarHeadLayoutBinding;
import com.google.android.material.navigation.NavigationView;

public class MainAdminActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageView imageMenu, bell;
    MainAdminActivity_ModelView modelView = new MainAdminActivity_ModelView();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainAdminBinding binding = (ActivityMainAdminBinding) DataBindingUtil.setContentView(this, R.layout.activity_main_admin);
        DrawarAdminHeadLayoutBinding drawarAdminHeadLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.drawar_admin_head_layout, binding.navView, false);
        modelView.GetData();
        drawarAdminHeadLayoutBinding.setDrawerModelView(modelView);
        binding.navView.addHeaderView(drawarAdminHeadLayoutBinding.getRoot());

        AnhXa();
        Handle_Component();
    }


    public void AnhXa(){
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);
        bell = findViewById(R.id.bell);
        imageMenu = findViewById(R.id.imageMenu);

    }

    public void Handle_Component(){
        toggle = new ActionBarDrawerToggle(MainAdminActivity.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Drawer click event
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.mHome) {
                    Intent intent = new Intent(MainAdminActivity.this, MainAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mAsset) {
                    Intent intent = new Intent(MainAdminActivity.this, AssetAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mAssignment) {
                    Intent intent = new Intent(MainAdminActivity.this, AssignmentActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mProfile) {
                    Intent intent = new Intent(MainAdminActivity.this, ProfileAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mStaff) {
                    Toast.makeText(MainAdminActivity.this, "Staff", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mFeedback) {
                    Toast.makeText(MainAdminActivity.this, "Feedback", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mExport) {
                    Toast.makeText(MainAdminActivity.this, "Export", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mLogout) {
                    Intent intent = new Intent(MainAdminActivity.this, LoginActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                    finish();
                }
                return false;
            }
        });
        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAdminActivity.this, NotificationAdminActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
