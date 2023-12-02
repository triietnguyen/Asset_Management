package com.example.navigationdrawer.view.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.view.asset.AssetAdminActivity;
import com.example.navigationdrawer.view.assignment.AssignmentAdminActivity;
import com.example.navigationdrawer.databinding.ActivityMainAdminBinding;
import com.example.navigationdrawer.databinding.DrawarAdminHeadLayoutBinding;
//import com.example.navigationdrawer.databinding.DrawarHeadLayoutBinding;
import com.example.navigationdrawer.view.login.LoginActivity;
import com.example.navigationdrawer.view.notification.NotificationAdminActivity;
import com.example.navigationdrawer.view.profile.ProfileAdminActivity;
import com.example.navigationdrawer.view.report.ReportActivity;
import com.example.navigationdrawer.view.request.RequestAdminActivity;
import com.example.navigationdrawer.view.user.UserAdminActivity;
import com.google.android.material.navigation.NavigationView;

import com.example.navigationdrawer.viewmodel.admin.MainAdmin_ModelView;

public class MainAdminActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageView imageMenu, bell, img_device, img_staff, img_assign, img_request, img_export, img_logout;
    MainAdmin_ModelView modelView = new MainAdmin_ModelView();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainAdminBinding binding = (ActivityMainAdminBinding) DataBindingUtil.setContentView(this, R.layout.activity_main_admin);
        modelView.GetData();
        DrawarAdminHeadLayoutBinding drawarAdminHeadLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.drawar_admin_head_layout, binding.navView, false);
        if(modelView.getImage() != null){
            Uri uri = Uri.parse(modelView.getImage());
            drawarAdminHeadLayoutBinding.imgAdminDrawerPage.setImageURI(uri);
        }
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
        img_device = findViewById(R.id.img_device);
        img_staff = findViewById(R.id.img_staff);
        img_assign = findViewById(R.id.img_assign);
        img_request = findViewById(R.id.img_request);
        img_export = findViewById(R.id.img_export);
        img_logout = findViewById(R.id.img_logout);
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
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mAsset) {
                    Intent intent = new Intent(MainAdminActivity.this, AssetAdminActivity.class);
                    startActivity(intent);
                    finish();
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mAssignment) {
                    Intent intent = new Intent(MainAdminActivity.this, AssignmentAdminActivity.class);
                    startActivity(intent);
                    finish();
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mRequest) {
                    Intent intent = new Intent(MainAdminActivity.this, RequestAdminActivity.class);
                    startActivity(intent);
                    finish();
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mProfile) {
                    Intent intent = new Intent(MainAdminActivity.this, ProfileAdminActivity.class);
                    startActivity(intent);
                    finish();
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mStaff) {
                    Intent intent = new Intent(MainAdminActivity.this, UserAdminActivity.class);
                    startActivity(intent);
                    finish();
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mFeedback) {
                    Toast.makeText(MainAdminActivity.this, "Feedback", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mReport) {
                    Intent intent = new Intent(MainAdminActivity.this, ReportActivity.class);
                    startActivity(intent);
                    finish();
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mLogout) {
                    Intent intent = new Intent(MainAdminActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    drawerLayout.closeDrawers();
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

        img_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAdminActivity.this, AssetAdminActivity.class);
                startActivity(intent);
                finish();
            }
        });

        img_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAdminActivity.this, UserAdminActivity.class);
                startActivity(intent);
                finish();
            }
        });

        img_assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAdminActivity.this, AssignmentAdminActivity.class);
                startActivity(intent);
                finish();
            }
        });

        img_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAdminActivity.this, RequestAdminActivity.class);
                startActivity(intent);
                finish();
            }
        });

        img_export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAdminActivity.this, ReportActivity.class);
                startActivity(intent);
                finish();
            }
        });

        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAdminActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
