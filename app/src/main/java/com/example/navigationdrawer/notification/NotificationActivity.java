package com.example.navigationdrawer.notification;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.navigationdrawer.main.MainActivity;
import com.example.navigationdrawer.R;
import ViewModels.Login_ModelView;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {
    ImageView img_Back;
    ListView listView;
    ArrayList<String> ListNotification;
    private Login_ModelView loginModelView = new Login_ModelView();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        img_Back = (ImageView) findViewById(R.id.back_notification);
        listView = (ListView) findViewById(R.id.lv);
        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ListNotification = new ArrayList<>();
        ListNotification.add("A");
        ListNotification.add("B");
        ListNotification.add("A");
        ListNotification.add("B");
        ListNotification.add("A");
        ListNotification.add("B");
        ListNotification.add("A");
        ListNotification.add("B");
        ListNotification.add("A");
        ListNotification.add("B");
        ListNotification.add("A");
        ListNotification.add("B");
        ListNotification.add("A");
        ListNotification.add("B");
        ListNotification.add("A");
        ListNotification.add("B");
        ListNotification.add("A");
        ListNotification.add("B");

        Adapter adapter = new ArrayAdapter(NotificationActivity.this, android.R.layout.simple_list_item_1,ListNotification);
        listView.setAdapter((ListAdapter) adapter);
    }
}
