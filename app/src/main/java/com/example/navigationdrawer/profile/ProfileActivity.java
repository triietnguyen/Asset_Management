package com.example.navigationdrawer.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
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
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.navigationdrawer.asset.AssetActivity;
import com.example.navigationdrawer.assignment.AssignmentActivity;
import com.example.navigationdrawer.databinding.ActivityUserProfileBinding;
import com.example.navigationdrawer.login.LoginActivity;
import com.example.navigationdrawer.main.MainActivity;
import com.example.navigationdrawer.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.navigation.NavigationView;

import ViewModels.User.ProfileActivity_ModelView;


public class ProfileActivity extends AppCompatActivity {
    DrawerLayout drawer_Layout;
    NavigationView navigation_View;
    ActionBarDrawerToggle action_Toggle;
    ImageView imageMenu,img_User;
    Button btn_Change_Password;
    ProfileActivity_ModelView profileActivityModelView = new ProfileActivity_ModelView();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile);
        profileActivityModelView.GetData();
        binding.setProfileActivityModelView(profileActivityModelView);
        AnhXa();
        Handle_Component();
    }

    void AnhXa(){
        drawer_Layout = findViewById(R.id.drawer_layout);
        navigation_View = findViewById(R.id.nav_View);
        imageMenu = findViewById(R.id.imageMenu);
        img_User = (ImageView) findViewById(R.id.imgUser_ProfilePage);
        btn_Change_Password = (Button)findViewById(R.id.changepass);
    }

    void Handle_Component(){
        // Navigation Drawer------------------------------
        action_Toggle = new ActionBarDrawerToggle(ProfileActivity.this, drawer_Layout, R.string.open, R.string.close);
        drawer_Layout.addDrawerListener(action_Toggle);
        action_Toggle.syncState();


        // Drawer click event
        navigation_View.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.mHome) {
                    Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mAsset) {
                    Intent intent = new Intent(ProfileActivity.this, AssetActivity.class);
                    startActivity(intent);
                    finish();
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mAssignment) {
                    Intent intent = new Intent(ProfileActivity.this, AssignmentActivity.class);
                    startActivity(intent);
                    finish();
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mProfile) {
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mLogout) {
                    Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                    startActivity(intent);
                    drawer_Layout.closeDrawers();
                    finish();
                }
                return false;
            }
        });

        // App Bar Click Event
        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_Layout.openDrawer(GravityCompat.START);
            }
        });


        img_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(ProfileActivity.this)
                        .galleryOnly()
                        .start();
            }
        });

        btn_Change_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ChangePasswordProfileActivity.class);
                startActivity(intent);
            }
        });
        if(profileActivityModelView.getImage() != null){
            Uri uri = Uri.parse(profileActivityModelView.getImage());
            img_User.setImageURI(uri);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        profileActivityModelView.setImage(uri.toString());
        Toast.makeText(this, "uri "+uri, Toast.LENGTH_SHORT).show();
        img_User.setImageURI(uri);

    }
}
