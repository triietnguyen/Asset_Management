package com.example.navigationdrawer.request;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.assignment.AssignmentApdapter;
import com.example.navigationdrawer.assignment.new_request.NewRequestActivity;
import com.example.navigationdrawer.main.MainAdminActivity;

import java.util.ArrayList;
import java.util.List;

import Models.Assignment;

public class RequestAdminActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RequestAdminAdapter requestAdminAdapter;
    ImageView img_Back_RequestPage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_admin);
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
        List<Assignment> assignmentList = new ArrayList<>();
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        assignmentList.add(new Assignment("a","a","a","a","a","a","a","a"));
        return  assignmentList;
    }

    void AnhXa(){
        img_Back_RequestPage = (ImageView)findViewById(R.id.img_Back_RequestPage);
        recyclerView = findViewById(R.id.recycler_view);
    }

    void Handle_Component(){
        img_Back_RequestPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
