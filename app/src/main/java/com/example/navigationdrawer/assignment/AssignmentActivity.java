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

import com.example.navigationdrawer.assignment.new_request.NewRequestActivity;
import com.example.navigationdrawer.R;

import java.util.ArrayList;
import java.util.List;

import Models.Assignment;

public class AssignmentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AssignmentApdapter assignmentApdapter;
    ImageView img_Back_Assignment;
    Button btn_New_Request;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        AnhXa();
        Handle_Component();
        setRecycleView();
    }
    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assignmentApdapter = new AssignmentApdapter(this, getList());
        recyclerView.setAdapter(assignmentApdapter);
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
        img_Back_Assignment = (ImageView)findViewById(R.id.img_Back_AssignmentPage);
        btn_New_Request = (Button)findViewById(R.id.btn_New_Request_AssignmentPage);
        recyclerView = findViewById(R.id.recycler_view);
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
                Intent intent = new Intent(AssignmentActivity.this, NewRequestActivity.class);
                startActivity(intent);
            }
        });
    }
}
