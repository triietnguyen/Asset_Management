package com.example.navigationdrawer.assignment;

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

import com.example.navigationdrawer.asset.AssetAdminActivity;
import com.example.navigationdrawer.assignment.new_request.NewRequestAdminActivity;
import com.example.navigationdrawer.R;
import com.example.navigationdrawer.login.LoginActivity;
import com.example.navigationdrawer.main.MainAdminActivity;
import com.example.navigationdrawer.profile.ProfileAdminActivity;
import com.example.navigationdrawer.report.ReportActivity;
import com.example.navigationdrawer.request.RequestAdminActivity;
import com.example.navigationdrawer.user.UserAdminActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import Models.Assignment;
import ViewModels.Admin.AssignmentAdminActivity_ModelView;


public class AssignmentAdminActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    RecyclerView recycler_view_admin;

    AssignmentAdminAdapter adapter;
    ImageView imageMenu;
    Button btn_New_Request;
    AssignmentAdminActivity_ModelView assignmentAdminActivity_modelView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_admin);
        AnhXa();
        Handle_Component();
        setRecycleView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setRecycleView();
    }

    private void setRecycleView() {
        assignmentAdminActivity_modelView = new AssignmentAdminActivity_ModelView();
        recycler_view_admin.setHasFixedSize(true);
        recycler_view_admin.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AssignmentAdminAdapter(this, assignmentAdminActivity_modelView.GetAllAssignment());
        recycler_view_admin.setAdapter(adapter);
    }


    private List<Assignment> getList(){
        List<Assignment> assignment_list = new ArrayList<>();
        assignment_list.add(new Assignment("1","1","1","1","1","1","1","a"));

        return assignment_list;
    }


    void AnhXa(){
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);
        imageMenu = findViewById(R.id.imageMenu);
        btn_New_Request = (Button)findViewById(R.id.btn_New_Request_AssignmentPage);
        recycler_view_admin = findViewById(R.id.recycler_view_admin);
    }

    void Handle_Component(){
        // Navigation Drawer------------------------------
        toggle = new ActionBarDrawerToggle(AssignmentAdminActivity.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Drawer click event
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.mHome) {
                    Intent intent = new Intent(AssignmentAdminActivity.this, MainAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mAsset) {
                    Intent intent = new Intent(AssignmentAdminActivity.this, AssetAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mAssignment) {
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mRequest) {
                    Intent intent = new Intent(AssignmentAdminActivity.this, RequestAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mProfile) {
                    Intent intent = new Intent(AssignmentAdminActivity.this, ProfileAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mStaff) {
                    Intent intent = new Intent(AssignmentAdminActivity.this, UserAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mFeedback) {
                    Toast.makeText(AssignmentAdminActivity.this, "Feedback", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mReport) {
                    Intent intent = new Intent(AssignmentAdminActivity.this, ReportActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mLogout) {
                    Intent intent = new Intent(AssignmentAdminActivity.this, LoginActivity.class);
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

        btn_New_Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssignmentAdminActivity.this, NewRequestAdminActivity.class);
                startActivity(intent);
            }
        });
    }
}
