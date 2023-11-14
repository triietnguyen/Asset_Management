package com.example.navigationdrawer.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.databinding.ActivityAssetAdminBinding;
import com.example.navigationdrawer.databinding.ActivityStaffAdminBinding;
import com.example.navigationdrawer.model.Asset;
import com.example.navigationdrawer.model.User;
import com.example.navigationdrawer.view.asset.AssetAdminActivity;
import com.example.navigationdrawer.view.assignment.AssignmentAdminActivity;
import com.example.navigationdrawer.view.login.LoginActivity;
import com.example.navigationdrawer.view.main.MainAdminActivity;
import com.example.navigationdrawer.view.profile.ProfileAdminActivity;
import com.example.navigationdrawer.view.report.ReportActivity;
import com.example.navigationdrawer.view.request.RequestAdminActivity;
import com.example.navigationdrawer.viewmodel.admin.AssetAdminActivity_ModelView;
import com.google.android.material.navigation.NavigationView;

import com.example.navigationdrawer.viewmodel.admin.UserAdminActivity_ModelView;

import java.util.ArrayList;
import java.util.List;

public class UserAdminActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    RecyclerView recyclerView;
    UserAdminApdapter userAdminApdapter;
    UserAdminActivity_ModelView userAdminActivityModelView;
    ImageView imageMenu;

    Button create_User;
    ImageButton imgBtn_Search;
    String search = "";
    String choiceFilter ="All";
    Spinner spinner_filter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStaffAdminBinding _binding = DataBindingUtil.setContentView(this,R.layout.activity_staff_admin);
        userAdminActivityModelView = new UserAdminActivity_ModelView();
        _binding.setUserAdminActivityModelView(userAdminActivityModelView);
        AnhXa();
        Handle_Component();
        setRecycleView();
        UserFilterAdapter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                setRecycleView();
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    public void UserFilterAdapter(){

        List<String> listFilter = new ArrayList<>();
        listFilter.add("All");
        listFilter.add("ID");
        listFilter.add("Name");
        listFilter.add("Joined Date");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listFilter);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_filter.setAdapter(arrayAdapter);
        spinner_filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                choiceFilter = parentView.getItemAtPosition(position).toString();
                // Làm gì đó với mục đã chọn
                Toast.makeText(getApplicationContext(), choiceFilter, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

    }
    private void setRecycleView() {
        if(userAdminActivityModelView.getSearchStr() != null) {
            search = userAdminActivityModelView.getSearchStr();
        }
        List<User> listUser = userAdminActivityModelView.GetUsersBySearch(search,choiceFilter);
        if(listUser == null) return;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdminApdapter = new UserAdminApdapter(this, listUser);
        recyclerView.setAdapter(userAdminApdapter);
    }

    void AnhXa(){
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);
        imageMenu = findViewById(R.id.imageMenu);
        create_User = (Button) findViewById(R.id.btn_Create_User);
        recyclerView = findViewById(R.id.recycler_view_staff_layout_admin);
        imgBtn_Search = (ImageButton) findViewById(R.id.imgBtn_Search_AssetAdmin_Page);
        spinner_filter = findViewById(R.id.spinner_filter);
    }
    void Handle_Component(){

        create_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserAdminActivity.this, CreateUserAdminActivity.class);
                startActivityForResult(intent,1);
            }
        });
        // Navigation Drawer------------------------------
        toggle = new ActionBarDrawerToggle(UserAdminActivity.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Drawer click event
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.mHome) {
                    Intent intent = new Intent(UserAdminActivity.this, MainAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mAsset) {
                    Intent intent = new Intent(UserAdminActivity.this, AssetAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mAssignment) {
                    Intent intent = new Intent(UserAdminActivity.this, AssignmentAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mRequest) {
                    Intent intent = new Intent(UserAdminActivity.this, RequestAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mProfile) {
                    Intent intent = new Intent(UserAdminActivity.this, ProfileAdminActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mStaff) {
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mFeedback) {
                    Toast.makeText(UserAdminActivity.this, "Feedback", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mReport) {
                    Intent intent = new Intent(UserAdminActivity.this, ReportActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.mLogout) {
                    Intent intent = new Intent(UserAdminActivity.this, LoginActivity.class);
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

        imgBtn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecycleView();
            }
        });

    }
}
