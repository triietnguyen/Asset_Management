package com.example.navigationdrawer.view.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.view.assignment.AssignmentActivity;

import com.example.navigationdrawer.databinding.ActivityMainBinding;
import com.example.navigationdrawer.databinding.DrawarUserHeadLayoutBinding;
import com.example.navigationdrawer.view.login.LoginActivity;
import com.example.navigationdrawer.view.notification.NotificationActivity;
import com.example.navigationdrawer.view.profile.ProfileActivity;
import com.example.navigationdrawer.view.request.RequestActivity;
import com.google.android.material.navigation.NavigationView;

import com.example.navigationdrawer.viewmodel.user.Main_ModelView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer_Layout;
    NavigationView navigation_View;
    ActionBarDrawerToggle action_Toggle;
    ImageView img_Menu, img_Notification, img_request, img_assign, img_support, img_logout;
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
        img_request = findViewById(R.id.img_request);
        img_assign = findViewById(R.id.img_assign);
        img_support = findViewById(R.id.img_support);
        img_logout = findViewById(R.id.img_logout);
    }
    private void openDialog(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_diaglog_feedback);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if(Gravity.BOTTOM ==gravity){
            dialog.setCancelable(true);
        }
        else{
            dialog.setCancelable(false);
        }

        Button No = dialog.findViewById(R.id.btn_no_thanks);
        Button Send = dialog.findViewById(R.id.btn_send);

        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Sent", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
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
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mAssignment) {
                    Intent intent = new Intent(MainActivity.this, AssignmentActivity.class);
                    startActivity(intent);
                    finish();
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mRequest) {
                    Intent intent = new Intent(MainActivity.this, RequestActivity.class);
                    startActivity(intent);
                    finish();
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mProfile) {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    finish();
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mContact) {
                    openDialog(Gravity.CENTER);
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

        img_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RequestActivity.class);
                startActivity(intent);
                finish();
            }
        });

        img_assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AssignmentActivity.class);
                startActivity(intent);
                finish();
            }
        });

        img_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(Gravity.CENTER);
            }
        });

        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

