package com.example.navigationdrawer.view.asset;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.databinding.ActivityNewAssetAdminBinding;

import java.util.ArrayList;
import java.util.List;

import com.example.navigationdrawer.model.Category;
import com.example.navigationdrawer.viewmodel.admin.AssetAdminActivity_ModelView;

public class CreateAssetAdminActivity extends AppCompatActivity {
    Spinner spinnerCategoryRequest,asset_spinner_state ;
    SharedPreferences sharedPreferences;
    Button img_Cancel_Create;
    AssetAdminActivity_ModelView assetAdminActivityModelView;
    List<String> listCategoryName = new ArrayList<>();
    List<String> listState = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("Asset", Context.MODE_PRIVATE);
        ActivityNewAssetAdminBinding activityNewAssetAdminBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_asset_admin);
        assetAdminActivityModelView = new AssetAdminActivity_ModelView();
        activityNewAssetAdminBinding.setAssetAdminActivityModelView(assetAdminActivityModelView);
        AnhXa();
        Handle_Component();
        CategoryRequestAdapter();
        StateAsset();
    }
    void AnhXa(){
        spinnerCategoryRequest = (Spinner) findViewById(R.id.spinnerCategoryRequest);
        asset_spinner_state = (Spinner) findViewById(R.id.asset_spinner_state);
        img_Cancel_Create = (Button) findViewById(R.id.img_Cancel_Create);
    }
    void Handle_Component(){
        img_Cancel_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAssetAdminActivity.this, AssetAdminActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void StateAsset(){
        listState.add("Not Available");
        listState.add("Available");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listState);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        asset_spinner_state.setAdapter(arrayAdapter);
        asset_spinner_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                for(int i =0; i<listState.size();i++){
                    if(listState.get(i).equalsIgnoreCase("Not Available")){
                        listState.set(i,"0");
                    }
                    else{
                        listState.set(i,"1");
                    }
                }
                // Sự kiện xảy ra khi một mục được chọn
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String selectedId = listState.get(position);
                editor.putString("Status",selectedId);
                editor.commit();

                // Làm gì đó với mục đã chọn
                Toast.makeText(getApplicationContext(), "Id đã chọn: " + selectedId, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }
    public void CategoryRequestAdapter(){

        List<Category> listCategory = assetAdminActivityModelView.GetAllCategory();
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
                editor.putString("Category_id",selectedId);
                editor.commit();

                // Làm gì đó với mục đã chọn
                Toast.makeText(getApplicationContext(), "Id đã chọn: " + selectedId, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }
}
