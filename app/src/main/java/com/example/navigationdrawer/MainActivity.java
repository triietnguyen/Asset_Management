package com.example.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer_Layout;
    NavigationView navigation_View;
    ActionBarDrawerToggle action_Toggle;
    ImageView img_Menu, img_Notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

