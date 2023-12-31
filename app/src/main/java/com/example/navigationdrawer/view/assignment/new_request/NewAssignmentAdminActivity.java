package com.example.navigationdrawer.view.assignment.new_request;

import android.content.Context;
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
import com.example.navigationdrawer.databinding.ActivityNewAssignmentAdminBinding;

import java.util.ArrayList;

import java.util.List;

import com.example.navigationdrawer.model.Asset;
import com.example.navigationdrawer.model.Category;
import com.example.navigationdrawer.model.User;
import com.example.navigationdrawer.viewmodel.admin.NewRequestAdminActivity_ModelView;


public class NewAssignmentAdminActivity extends AppCompatActivity {

    Button img_Back_NewRequest;
    Spinner spinnerCategoryRequest;
    Spinner spinnerUserRequest;
    Spinner spinnerAssetRequest;

    NewRequestAdminActivity_ModelView newRequestAdminActivityModelView ;
    List<String> listFullNameUser = new ArrayList<>();
    List<String> listCategoryName = new ArrayList<>();
    List<String> listAssetName = new ArrayList<>();
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("Assignment", Context.MODE_PRIVATE);
        ActivityNewAssignmentAdminBinding _binding = DataBindingUtil.setContentView(this,R.layout.activity_new_assignment_admin);
        newRequestAdminActivityModelView = new NewRequestAdminActivity_ModelView();
        _binding.setNewRequestAdminActivityModelView(newRequestAdminActivityModelView);

        AnhXa();
        Handle_Component();
        UserAdapter();
        CategoryRequestAdapter();

    }
    public void AnhXa(){
        img_Back_NewRequest = (Button) findViewById(R.id.img_Back_NewRequestPage);
        spinnerCategoryRequest = (Spinner) findViewById(R.id.category_spinner);
        spinnerAssetRequest = (Spinner) findViewById(R.id.asset_spinner);
        spinnerUserRequest = (Spinner) findViewById(R.id.user_spinner);
    }
    public void Handle_Component(){
        img_Back_NewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    public void CategoryRequestAdapter(){

        List<Category> listCategory = newRequestAdminActivityModelView.GetAllCategory();
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

                List<Asset> listAssetByCategoryName = newRequestAdminActivityModelView.GetAllAssetByCategory(selectedItem);
                AssetRequestAdapter(listAssetByCategoryName);

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

                }
            });
        }

    }

    public void UserAdapter(){

        List<User> listUser = newRequestAdminActivityModelView.GetAllUser();

        listFullNameUser.clear();
        for (User user : listUser) {
            listFullNameUser.add(user.getName());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listFullNameUser);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUserRequest.setAdapter(arrayAdapter);

        spinnerUserRequest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                SharedPreferences.Editor editor = sharedPreferences.edit();

                User selectedUser = listUser.get(position);
                String selectedUserId = selectedUser.getId(); // Lấy UID

                editor.putString("User_id",selectedUserId);
                editor.commit();

                Toast.makeText(NewAssignmentAdminActivity.this, "SelectUserID: " + selectedUserId, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Sự kiện xảy ra khi không có mục nào được chọn
            }
        });
    }
}
