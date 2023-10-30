package com.example.navigationdrawer.assignment.new_request;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
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

import Models.Asset;
import Models.Category;
import ViewModels.User.NewRequestActivity_ModelView;
import java.util.Calendar;

public class NewRequestActivity extends AppCompatActivity {

    ImageView img_Back_NewRequest;
    EditText edt_Date;
    Spinner spinnerCategoryRequest;
    Spinner spinnerAssetRequest;
    NewRequestActivity_ModelView newRequestActivityModelView;

    List<String> listCategoryName = new ArrayList<>();
    List<String> listAssetName = new ArrayList<>();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("Assignment", Context.MODE_PRIVATE);
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
        spinnerCategoryRequest = (Spinner) findViewById(R.id.category_spinner);
        spinnerAssetRequest = (Spinner) findViewById(R.id.asset_spinner);
    }
    public void Handle_Component(){
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
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                edt_Date.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year,month,date);
        datePickerDialog.show();
    }

    public void CategoryRequestAdapter(){

        List<Category> listCategory = newRequestActivityModelView.GetAllCategory();
        listCategoryName.clear();
        for(Category c : listCategory){
            listCategoryName.add(c.GetName());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listCategoryName);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoryRequest.setAdapter(arrayAdapter);

        spinnerCategoryRequest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Sự kiện xảy ra khi một mục được chọn
                SharedPreferences.Editor editor = sharedPreferences.edit();

                Category c = listCategory.get(position);
                String selectedId = c.GetID();
                String selectedItem = c.GetName();

                List<Asset> listAssetByCategoryName = newRequestActivityModelView.GetAllAssetByCategory(selectedItem);
                AssetRequestAdapter(listAssetByCategoryName);

                editor.putString("Category_id",selectedId);
                editor.commit();

                // Làm gì đó với mục đã chọn
                Toast.makeText(getApplicationContext(), "Id đã chọn: " + selectedId, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Sự kiện xảy ra khi không có mục nào được chọn
            }
        });
    }


    public void AssetRequestAdapter(List<Asset> listAssetByCategory){
        listAssetName.clear();
        for(Asset a : listAssetByCategory){
            listAssetName.add(a.getAsset_name());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listAssetName);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAssetRequest.setAdapter(arrayAdapter);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(listAssetName.size() == 0){
            editor.putString("Asset_id","");
            editor.commit();
            return;
        }
        else{
            spinnerAssetRequest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


                    Asset SelectedItem = listAssetByCategory.get(position);
                    String Assetid = SelectedItem.getAsset_id();

                    editor.putString("Asset_id",Assetid);
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Id đã chọn: " + Assetid, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // Sự kiện xảy ra khi không có mục nào được chọn
                }
            });
        }

    }
}
