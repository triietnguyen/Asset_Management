package com.example.navigationdrawer.view.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.example.navigationdrawer.databinding.ActivityAssignmentAdminBinding;
import com.example.navigationdrawer.databinding.ActivityAssignmentBinding;
import com.example.navigationdrawer.model.Assignment;
import com.example.navigationdrawer.view.login.LoginActivity;
import com.example.navigationdrawer.view.main.MainActivity;
import com.example.navigationdrawer.view.profile.ProfileActivity;
import com.example.navigationdrawer.view.request.RequestActivity;
import com.example.navigationdrawer.viewmodel.admin.AssignmentAdminActivity_ModelView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import com.example.navigationdrawer.model.MyApplication;
import com.example.navigationdrawer.model.User;
import com.example.navigationdrawer.viewmodel.user.AssignmentActivity_ModelView;

public class AssignmentActivity extends AppCompatActivity {
    DrawerLayout drawer_Layout;
    NavigationView navigation_View;
    ActionBarDrawerToggle action_Toggle;

    RecyclerView recyclerView;
    AssignmentApdapter assignmentApdapter;
    ImageView imageMenu;
    Spinner spinnerFilter;
    AssignmentActivity_ModelView assignmentActivityModelView;
    String choiceFilter ="All";
    String search = "";
    ImageButton imgBtn_Search;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAssignmentBinding _binding = DataBindingUtil.setContentView(this,R.layout.activity_assignment);
        assignmentActivityModelView = new AssignmentActivity_ModelView();
        _binding.setAssignmentActivityModelView(assignmentActivityModelView);

        AnhXa();
        Handle_Component();
        setRecycleView();
        AssignmentFilterAdapter();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e("test","test");
        setRecycleView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setRecycleView();
    }

    public void AssignmentFilterAdapter(){

        List<String> listFilter = new ArrayList<>();
        listFilter.add("All");
        listFilter.add("Asset Code");
        listFilter.add("Asset Name");
        listFilter.add("Category");
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


    private void setRecycleView() {
        User u = new User();
        String userID = u.GetNameUserByEmail(MyApplication.getInstance().GetSharedData());

        if(assignmentActivityModelView.getSearch() != null) {
            search = assignmentActivityModelView.getSearch();
        }
        List<Assignment> listAssign = assignmentActivityModelView.GetAssignmentBySearch(search,choiceFilter,userID);
        if(listAssign == null) return;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assignmentApdapter = new AssignmentApdapter(this, listAssign);
        recyclerView.setAdapter(assignmentApdapter);
    }


    void AnhXa(){
        drawer_Layout = findViewById(R.id.drawer_layout);
        navigation_View = findViewById(R.id.nav_View);
        imageMenu = findViewById(R.id.imageMenu);
        recyclerView = findViewById(R.id.recycler_view);
        spinnerFilter = findViewById(R.id.spinner_filter);
        imgBtn_Search = (ImageButton) findViewById(R.id.imgBtn_Search_Assignment_Page);
    }

    void Handle_Component(){
        // Navigation Drawer------------------------------
        action_Toggle = new ActionBarDrawerToggle(AssignmentActivity.this, drawer_Layout, R.string.open, R.string.close);
        drawer_Layout.addDrawerListener(action_Toggle);
        action_Toggle.syncState();


        // Drawer click event
        navigation_View.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.mHome) {
                    Intent intent = new Intent(AssignmentActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mAssignment) {
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mRequest) {
                    Intent intent = new Intent(AssignmentActivity.this, RequestActivity.class);
                    startActivity(intent);
                    finish();
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mProfile) {
                    Intent intent = new Intent(AssignmentActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    finish();
                    drawer_Layout.closeDrawers();
                }
                else if (itemId == R.id.mLogout) {
                    Intent intent = new Intent(AssignmentActivity.this, LoginActivity.class);
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

        imgBtn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecycleView();
            }
        });

    }
}
