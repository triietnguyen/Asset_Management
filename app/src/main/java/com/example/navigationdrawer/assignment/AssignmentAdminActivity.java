package com.example.navigationdrawer.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawer.assignment.new_request.NewRequestAdminActivity;
import com.example.navigationdrawer.R;

import java.util.ArrayList;
import java.util.List;

import Models.Assignment;


public class AssignmentAdminActivity extends AppCompatActivity {

    RecyclerView recycler_view_admin;

    AssignmentAdminAdapter adapter;
    ImageView img_Back_Assignment;
    Button btn_New_Request;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_admin);
        AnhXa();
        Handle_Component();
        setRecycleView();
    }

    private void setRecycleView() {
        recycler_view_admin.setHasFixedSize(true);
        recycler_view_admin.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AssignmentAdminAdapter(this, getList());
        recycler_view_admin.setAdapter(adapter);
    }

    private List<Assignment> getList(){
        List<Assignment> assignment_list = new ArrayList<>();
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignment_list.add(new Assignment("a","a","a","a","a","a","a","a"));
        return assignment_list;
    }


    void AnhXa(){
        img_Back_Assignment = (ImageView)findViewById(R.id.img_Back_AssignmentPage);
        btn_New_Request = (Button)findViewById(R.id.btn_New_Request_AssignmentPage);
        recycler_view_admin = findViewById(R.id.recycler_view_admin);
    }

    void Handle_Component(){
        img_Back_Assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
