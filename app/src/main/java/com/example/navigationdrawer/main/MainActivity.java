package com.example.navigationdrawer.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.asset.AssetActivity;
import com.example.navigationdrawer.assignment.AssignmentActivity;

import com.example.navigationdrawer.databinding.ActivityMainBinding;
import com.example.navigationdrawer.databinding.DrawarUserHeadLayoutBinding;
import com.example.navigationdrawer.login.LoginActivity;
import com.example.navigationdrawer.notification.NotificationActivity;
import com.example.navigationdrawer.profile.ProfileActivity;
import com.google.android.material.navigation.NavigationView;

import ViewModels.User.Main_ModelView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer_Layout;
    NavigationView navigation_View;
    ActionBarDrawerToggle action_Toggle;
    ImageView img_Menu, img_Notification;
    Main_ModelView modelView = new Main_ModelView();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = (ActivityMainBinding) DataBindingUtil.setContentView(this, R.layout.activity_main);

        modelView.GetData();

        DrawarUserHeadLayoutBinding drawarUserHeadLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.drawar_user_head_layout, binding.navView, false);
        if(modelView.getImage() != null){
            Uri uri = Uri.parse(modelView.getImage());
            drawarUserHeadLayoutBinding.imgUserDrawerPage.setImageURI(uri);
        }
        drawarUserHeadLayoutBinding.setDrawerModelView(modelView);

        binding.navView.addHeaderView(drawarUserHeadLayoutBinding.getRoot());

        AnhXa();
        Handle_Component();
    }
    void AnhXa(){
        drawer_Layout = findViewById(R.id.drawer_layout);
        navigation_View = findViewById(R.id.nav_View);
        img_Notification = findViewById(R.id.bell);
        img_Menu = findViewById(R.id.imageMenu);
    }

    void Handle_Component(){
        // Navigation Drawer------------------------------
        action_Toggle = new ActionBarDrawerToggle(MainActivity.this, drawer_Layout, R.string.open, R.string.close);
        drawer_Layout.addDrawerListener(action_Toggle);
        action_Toggle.syncState();


        // Drawer click event
        navigation_View.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.mHome) {
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mAsset) {
                    Intent intent = new Intent(MainActivity.this, AssetActivity.class);
                    startActivity(intent);
                    finish();
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mAssignment) {
                    Intent intent = new Intent(MainActivity.this, AssignmentActivity.class);
                    startActivity(intent);
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mProfile) {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    finish();
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mLogout) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    drawer_Layout.closeDrawers();
                    finish();
                }
                return false;
            }
        });

        // App Bar Click Event
        img_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_Layout.openDrawer(GravityCompat.START);
            }
        });

        img_Notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

