package com.example.navigationdrawer.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.navigationdrawer.asset.AssetAdminActivity;
import com.example.navigationdrawer.assignment.AssignmentAdminActivity;
import com.example.navigationdrawer.databinding.ActivityAdminProfileBinding;
import com.example.navigationdrawer.login.LoginActivity;
import com.example.navigationdrawer.main.MainAdminActivity;
import com.example.navigationdrawer.R;
import com.example.navigationdrawer.report.ReportActivity;
import com.example.navigationdrawer.request.RequestAdminActivity;
import com.example.navigationdrawer.user.UserAdminActivity;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.navigation.NavigationView;

import ViewModels.Admin.ProfileAdminActivity_ModelView;


public class ProfileAdminActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageView imageMenu,imgAdmin;
    Button btn_Change_Password;
    ProfileAdminActivity_ModelView profileActivityModelView = new ProfileAdminActivity_ModelView();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAdminProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_profile);
        profileActivityModelView.GetData();
        binding.setProfileAdminActivityModelView(profileActivityModelView);
        AnhXa();
        Handle_Component();
    }

    void AnhXa(){
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);
        imageMenu = findViewById(R.id.imageMenu);
        btn_Change_Password = (Button)findViewById(R.id.changepass);
        imgAdmin = (ImageView) findViewById(R.id.imgAdmin_ProfilePage);
    }

    void Handle_Component(){
        toggle = new ActionBarDrawerToggle(ProfileAdminActivity.this, drawerLayout, R.string.open, R.string.close);
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
                    Intent intent = new Intent(ProfileAdminActivity.this, AssetAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mAssignment) {
                    Intent intent = new Intent(ProfileAdminActivity.this, AssignmentAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mRequest) {
                    Intent intent = new Intent(ProfileAdminActivity.this, RequestAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mProfile) {
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mStaff) {
                    Intent intent = new Intent(ProfileAdminActivity.this, UserAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mFeedback) {
                    Toast.makeText(ProfileAdminActivity.this, "Feedback", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mReport) {
                    Intent intent = new Intent(ProfileAdminActivity.this, ReportActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mLogout) {
                    Intent intent = new Intent(ProfileAdminActivity.this, LoginActivity.class);
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

        btn_Change_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileAdminActivity.this, ChangePasswordProfileActivity.class);
                startActivity(intent);
            }
        });
        imgAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(ProfileAdminActivity.this)
                        .galleryOnly()
                        .start();
            }
        });

        if(profileActivityModelView.getImage() != null){
            Uri uri = Uri.parse(profileActivityModelView.getImage());
            imgAdmin.setImageURI(uri);
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        profileActivityModelView.setImage(uri.toString());
        Log.e("uri",uri.toString());
        Toast.makeText(this, "uri "+uri, Toast.LENGTH_SHORT).show();
        imgAdmin.setImageURI(uri);

    }
}
