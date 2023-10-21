package com.example.navigationdrawer.user;

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
import com.example.navigationdrawer.main.MainAdminActivity;

import java.util.ArrayList;
import java.util.List;

import Models.Asset;
import Models.User;

public class UserAdminActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    UserAdminApdapter userAdminApdapter;
    ImageView img_Back_User_Admin;

    Button create_User;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_admin);
        AnhXa();
        Handle_Component();
        setRecycleView();
    }

    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdminApdapter = new UserAdminApdapter(this, getList());
        recyclerView.setAdapter(userAdminApdapter);
    }

    private List<User> getList() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("a","a","a","1","a","a","a","a","a","a","a"));
        userList.add(new User("a","a","a","1","a","a","a","a","a","a","a"));
        userList.add(new User("a","a","a","1","a","a","a","a","a","a","a"));
        userList.add(new User("a","a","a","1","a","a","a","a","a","a","a"));
        userList.add(new User("a","a","a","1","a","a","a","a","a","a","a"));
        userList.add(new User("a","a","a","1","a","a","a","a","a","a","a"));
        userList.add(new User("a","a","a","1","a","a","a","a","a","a","a"));
        userList.add(new User("a","a","a","1","a","a","a","a","a","a","a"));
        userList.add(new User("a","a","a","1","a","a","a","a","a","a","a"));
        userList.add(new User("a","a","a","1","a","a","a","a","a","a","a"));
        userList.add(new User("a","a","a","1","a","a","a","a","a","a","a"));
        userList.add(new User("a","a","a","1","a","a","a","a","a","a","a"));
        userList.add(new User("a","a","a","1","a","a","a","a","a","a","a"));
        userList.add(new User("a","a","a","1","a","a","a","a","a","a","a"));
        userList.add(new User("a","a","a","1","a","a","a","a","a","a","a"));
        userList.add(new User("a","a","a","1","a","a","a","a","a","a","a"));
        userList.add(new User("a","a","a","1","a","a","a","a","a","a","a"));
        userList.add(new User("a","a","a","1","a","a","a","a","a","a","a"));
        userList.add(new User("a","a","a","1","a","a","a","a","a","a","a"));
        userList.add(new User("a","a","a","1","a","a","a","a","a","a","a"));
        userList.add(new User("a","a","a","1","a","a","a","a","a","a","a"));

        return  userList;
    }

    void AnhXa(){
        create_User = (Button) findViewById(R.id.btn_Create_User);
        img_Back_User_Admin = (ImageView) findViewById(R.id.back_Staff_AdminLayout);
        recyclerView = findViewById(R.id.recycler_view_staff_layout_admin);
    }
    void Handle_Component(){

        create_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserAdminActivity.this, CreateUserAdminActivity.class);
                startActivity(intent);
            }
        });
        img_Back_User_Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserAdminActivity.this, MainAdminActivity.class);
                startActivity(intent);
            }
        });

    }
}
