package com.example.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainAdminActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageView imageMenu, bell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        // Navigation Drawer------------------------------
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);

        bell = findViewById(R.id.bell);
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
                    Toast.makeText(MainAdminActivity.this, "Dashboard", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mAssignment) {
                    Intent intent = new Intent(MainAdminActivity.this, AssignmentActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mProfile) {
                    Intent intent = new Intent(MainAdminActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainAdminActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mStaff) {
                    Intent intent = new Intent(MainAdminActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainAdminActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                    finish();
                }
                else if (itemId == R.id.mFeedback) {
                    Intent intent = new Intent(MainAdminActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainAdminActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                    finish();
                }
                else if (itemId == R.id.mExport) {
                    Intent intent = new Intent(MainAdminActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainAdminActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                    finish();
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

        // App Bar Click Event
        imageMenu = findViewById(R.id.imageMenu);
        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        bell = findViewById(R.id.bell);
        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAdminActivity.this, NotificationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
