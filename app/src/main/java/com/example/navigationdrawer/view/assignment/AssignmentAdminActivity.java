package com.example.navigationdrawer.view.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import com.example.navigationdrawer.databinding.ActivityAssignmentAdminBinding;
import com.example.navigationdrawer.model.Assignment;
import com.example.navigationdrawer.view.asset.AssetAdminActivity;
import com.example.navigationdrawer.view.assignment.new_request.NewAssignmentAdminActivity;
import com.example.navigationdrawer.R;
import com.example.navigationdrawer.view.login.LoginActivity;
import com.example.navigationdrawer.view.main.MainAdminActivity;
import com.example.navigationdrawer.view.profile.ProfileAdminActivity;
import com.example.navigationdrawer.view.report.ReportActivity;
import com.example.navigationdrawer.view.request.RequestAdminActivity;
import com.example.navigationdrawer.view.user.UserAdminActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import com.example.navigationdrawer.viewmodel.admin.AssignmentAdminActivity_ModelView;


public class AssignmentAdminActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    RecyclerView recycler_view_admin;
    AssignmentAdminAdapter adapter;
    ImageView imageMenu;
    Button btn_New_Request;
    ImageButton imgBtn_Search;
    Spinner spinnerFilter,spinner_state_filter;
    EditText edt_Search;
    AssignmentAdminActivity_ModelView assignmentAdminActivity_modelView;
    String choiceFilter ="All", choiceStatusFilter ="All";
    String search = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAssignmentAdminBinding _binding = DataBindingUtil.setContentView(this,R.layout.activity_assignment_admin);
        assignmentAdminActivity_modelView = new AssignmentAdminActivity_ModelView();
        _binding.setAssignmentAdminActivityModelView(assignmentAdminActivity_modelView);
        AnhXa();
        Handle_Component();
        setRecycleView();
        AssignmentFilterAdapter();
        StatusFilterAdapter();
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

    public void AssignmentFilterAdapter(){

        List<String> listFilter = new ArrayList<>();
        listFilter.add("All");
        listFilter.add("ID");
        listFilter.add("Asset Code");
        listFilter.add("Asset Name");
        listFilter.add("Category");
        listFilter.add("Assigned To");
        listFilter.add("Assigned By");
        listFilter.add("Assigned Date");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listFilter);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFilter.setAdapter(arrayAdapter);
        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    public void StatusFilterAdapter(){

        List<String> listFilter = new ArrayList<>();
        listFilter.add("All");
        listFilter.add("Assigned");
        listFilter.add("Returned");
        listFilter.add("Returning");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listFilter);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_state_filter.setAdapter(arrayAdapter);
        spinner_state_filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                choiceStatusFilter = parentView.getItemAtPosition(position).toString();
                // Làm gì đó với mục đã chọn
                Toast.makeText(getApplicationContext(), choiceStatusFilter, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

    }


    private void setRecycleView() {
        if(assignmentAdminActivity_modelView.getSearchStr() != null) {
            search = assignmentAdminActivity_modelView.getSearchStr();
        }
        List<Assignment> listAssign = assignmentAdminActivity_modelView.GetAssignmentBySearch(search,choiceFilter, choiceStatusFilter);
        if(listAssign == null) return;
        recycler_view_admin.setHasFixedSize(true);
        recycler_view_admin.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AssignmentAdminAdapter(this,listAssign);
        recycler_view_admin.setAdapter(adapter);
    }

    void AnhXa(){
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);
        imageMenu = findViewById(R.id.imageMenu);
        btn_New_Request = (Button)findViewById(R.id.btn_New_Request_AssignmentPage);
        recycler_view_admin = findViewById(R.id.recycler_view_admin);
        spinnerFilter = findViewById(R.id.spinner_filter);
        spinner_state_filter = findViewById(R.id.spinner_state_filter);
        edt_Search = (EditText) findViewById(R.id.edt_Search_AssigmentPage);
        imgBtn_Search = (ImageButton) findViewById(R.id.imgBtn_Search_AssignmentAdmin_Page);
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
                Intent intent = new Intent(AssignmentAdminActivity.this, NewAssignmentAdminActivity.class);
                startActivityForResult(intent, 1);
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
