package com.example.navigationdrawer.assignment.new_request;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.databinding.ActivityNewRequestBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Models.Category;
import ViewModels.User.NewRequestActivity_ModelView;
import java.util.Calendar;

public class NewRequestActivity extends AppCompatActivity {

    ImageView img_Back_NewRequest;
    EditText edt_Date;
    Button btn_Save;

    Spinner spinnerCategoryRequest;
    Spinner spinnerAssetRequest;
    NewRequestActivity_ModelView newRequestActivityModelView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_request);
        ActivityNewRequestBinding _binding = DataBindingUtil.setContentView(this,R.layout.activity_new_request);
        newRequestActivityModelView = new NewRequestActivity_ModelView();
        _binding.setNewRequestActivityModelView(newRequestActivityModelView);

        AnhXa();
        Handle_Component();
        CategoryRequestAdapter();

    }
    public void AnhXa(){
        img_Back_NewRequest = (ImageView) findViewById(R.id.img_Back_NewRequestPage);
        edt_Date = (EditText)findViewById(R.id.edt_Date_NewRequestPage);
        btn_Save = (Button)findViewById(R.id.btn_Save_NewRequestPage);
        spinnerCategoryRequest = (Spinner) findViewById(R.id.category_spinner);
        spinnerAssetRequest = (Spinner) findViewById(R.id.asset_spinner);
    }
    public void Handle_Component(){
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewRequestActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        img_Back_NewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edt_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pick_Date();
            }
        });
    }

    private void Pick_Date(){
        Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edt_Date.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year,month,date);
        datePickerDialog.show();
    }

    public void CategoryRequestAdapter(){
        //Dang Fix
        List<Category> listCategory = newRequestActivityModelView.GetAllCategory();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listCategory);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoryRequest.setAdapter(arrayAdapter);

        spinnerCategoryRequest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Sự kiện xảy ra khi một mục được chọn
//                String selectedItem = (String) parentView.getItemAtPosition(position);
//                List<Asset> listCategoryName = newRequestActivityModelView.GetAllAssetByCategory(selectedItem);
//
//                AssetRequestAdapter(listCategoryName);
//                // Làm gì đó với mục đã chọn
//                Toast.makeText(getApplicationContext(), "Bạn đã chọn: " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Sự kiện xảy ra khi không có mục nào được chọn
            }
        });
    }

    public void AssetRequestAdapter(List<String> listCategoryName){

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listCategoryName);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAssetRequest.setAdapter(arrayAdapter);
    }
}
