package com.example.navigationdrawer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {
    ImageView img_Back;
    ListView listView;
    ArrayList<String> ListNotification;
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
                finish();
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
