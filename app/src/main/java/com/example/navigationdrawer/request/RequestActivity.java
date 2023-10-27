package com.example.navigationdrawer.request;

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

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.asset.AssetActivity;
import com.example.navigationdrawer.asset.AssetAdminActivity;
import com.example.navigationdrawer.assignment.AssignmentActivity;
import com.example.navigationdrawer.assignment.AssignmentAdminActivity;
import com.example.navigationdrawer.assignment.new_request.NewRequestActivity;
import com.example.navigationdrawer.assignment.new_request.NewRequestAdminActivity;
import com.example.navigationdrawer.login.LoginActivity;
import com.example.navigationdrawer.main.MainActivity;
import com.example.navigationdrawer.main.MainAdminActivity;
import com.example.navigationdrawer.notification.NotificationActivity;
import com.example.navigationdrawer.profile.ProfileActivity;
import com.example.navigationdrawer.profile.ProfileAdminActivity;
import com.example.navigationdrawer.report.ReportActivity;
import com.example.navigationdrawer.user.UserAdminActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import Models.Assignment;

public class RequestActivity extends AppCompatActivity {
    DrawerLayout drawer_Layout;
    NavigationView navigation_View;
    ActionBarDrawerToggle action_Toggle;

    RecyclerView recyclerView;
    RequestAdminAdapter requestAdminAdapter;
    ImageView img_Menu, img_Notification;
    Button btn_New_Request_AssignmentPage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        AnhXa();
        Handle_Component();
        setRecycleView();
    }
    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestAdminAdapter = new RequestAdminAdapter(this, getList());
        recyclerView.setAdapter(requestAdminAdapter);
    }

    private List<Assignment> getList() {
        Assignment assign = new Assignment();
        List<Assignment> assignmentList = assign.GetAllAssignment_NotHandler();
        return  assignmentList;
    }

    void AnhXa(){
        drawer_Layout = findViewById(R.id.drawer_layout);
        navigation_View = findViewById(R.id.nav_View);
        img_Menu = findViewById(R.id.imageMenu);
        img_Notification = findViewById(R.id.bell);
        btn_New_Request_AssignmentPage = (Button)findViewById(R.id.btn_New_Request_AssignmentPage);
        recyclerView = findViewById(R.id.recycler_view);
    }

    void Handle_Component(){
        // Navigation Drawer------------------------------
        action_Toggle = new ActionBarDrawerToggle(RequestActivity.this, drawer_Layout, R.string.open, R.string.close);
        drawer_Layout.addDrawerListener(action_Toggle);
        action_Toggle.syncState();


        // Drawer click event
        navigation_View.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.mHome) {
                    Intent intent = new Intent(RequestActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mAsset) {
                    Intent intent = new Intent(RequestActivity.this, AssetActivity.class);
                    startActivity(intent);
                    finish();
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mAssignment) {
                    Intent intent = new Intent(RequestActivity.this, AssignmentActivity.class);
                    startActivity(intent);
                    finish();
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mRequest) {
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mProfile) {
                    Intent intent = new Intent(RequestActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    finish();
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mLogout) {
                    Intent intent = new Intent(RequestActivity.this, LoginActivity.class);
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
                Intent intent = new Intent(RequestActivity.this, NotificationActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_New_Request_AssignmentPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestActivity.this, NewRequestActivity.class);
                startActivity(intent);
            }
        });
    }
}
