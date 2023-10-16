package com.example.navigationdrawer.assignment.new_request;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.navigationdrawer.R;

import java.util.ArrayList;

public class NewRequestAdminActivity extends AppCompatActivity {

    Button img_Back_NewRequest;
    EditText edt_Date;
    Button btn_Save;

    Spinner spinnerCategoryRequest;
    Spinner spinnerAssetRequest;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_request_admin);
        AnhXa();
        Handle_Component();
        CategoryRequestAdapter();
        AssetRequestAdapter();
    }
    public void AnhXa(){
        img_Back_NewRequest = (Button) findViewById(R.id.img_Back_NewRequestPage);
        edt_Date = (EditText)findViewById(R.id.edt_Date_NewRequestPage);
        btn_Save = (Button)findViewById(R.id.btn_Save_NewRequestPage);
        spinnerCategoryRequest = (Spinner) findViewById(R.id.category_spinner);
        spinnerAssetRequest = (Spinner) findViewById(R.id.asset_spinner);
    }
    public void Handle_Component(){
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewRequestAdminActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        img_Back_NewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void CategoryRequestAdapter(){
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Màn hình");
        arrayList.add("Bàn phím");
        arrayList.add("Chuột");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoryRequest.setAdapter(arrayAdapter);
    }

    public void AssetRequestAdapter(){
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Asus Tuf K1 ");
        arrayList.add("LG UltraWide");
        arrayList.add("Logitech G304");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAssetRequest.setAdapter(arrayAdapter);
    }
}
