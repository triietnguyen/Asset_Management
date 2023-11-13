package com.example.navigationdrawer.view.request.edit;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.databinding.ActivityEditRequestAdminBinding;
import com.example.navigationdrawer.databinding.ActivityNewRequestBinding;
import com.example.navigationdrawer.model.Asset;
import com.example.navigationdrawer.model.Category;
import com.example.navigationdrawer.view.assignment.AssignmentAdminActivity;
import com.example.navigationdrawer.view.request.RequestAdminActivity;
import com.example.navigationdrawer.viewmodel.admin.EditRequestAdminActivity_ModelView;
import com.example.navigationdrawer.viewmodel.user.NewRequestActivity_ModelView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditRequestAdminActivity extends AppCompatActivity {
    Button img_CancelPage,img_ButtonSave;
    Spinner spinnerCategoryRequest,spinnerAssetRequest,spinnerStateRequest;
    EditText edt_DateOfReturning_Layout,edt_DateOfIssue_Layout;
    EditRequestAdminActivity_ModelView editRequestAdminActivityModelView;
    List<String> listCategoryName = new ArrayList<>();
    List<String> listAssetName = new ArrayList<>();

    Bundle dataBundle;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("RequestList", Context.MODE_PRIVATE);
        dataBundle = getIntent().getExtras();
        ActivityEditRequestAdminBinding _binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_request_admin);
        editRequestAdminActivityModelView = new EditRequestAdminActivity_ModelView();
        _binding.setEditRequestAdminActivityModelView(editRequestAdminActivityModelView);
        AnhXa();
        HandleComponent();
        CategoryRequestAdapter();
        StateRequestAdapter();
    }

    public void AnhXa(){
        img_CancelPage = findViewById(R.id.img_CancelPage);
        spinnerCategoryRequest = (Spinner) findViewById(R.id.category_spinner);
        spinnerAssetRequest = (Spinner) findViewById(R.id.asset_spinner);
        spinnerStateRequest = (Spinner) findViewById(R.id.state_spinner);
        edt_DateOfReturning_Layout = (EditText) findViewById(R.id.edt_DateOfReturning_Layout);
        img_ButtonSave = (Button) findViewById(R.id.btn_Save_Page);
    }

    public void HandleComponent(){
        img_CancelPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditRequestAdminActivity.this, RequestAdminActivity.class);
                startActivity(intent);
                finish();
            }
        });
        edt_DateOfReturning_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pick_Date_Birth();
            }
        });

        Calendar calendar = Calendar.getInstance();
        int dateCalendar = calendar.get(Calendar.DATE);
        int monthCalendar = calendar.get(Calendar.MONTH);
        int yearCalendar = calendar.get(Calendar.YEAR);
        calendar.set(yearCalendar,monthCalendar,dateCalendar);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate_Str = simpleDateFormat.format(calendar.getTime());
        editRequestAdminActivityModelView.setEdt_currentDate(currentDate_Str);
    }

    public void CategoryRequestAdapter(){

        if (dataBundle != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            String assignmentID = dataBundle.getString("id");
            String category_Name = dataBundle.getString("categoryName");
            String id = Category.GetIDByCategory(category_Name);

            Category category = new Category(id,category_Name);

            List<Category> listCategory = new ArrayList<>();
            listCategory.add(category);

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

                    Category c = listCategory.get(position);
                    String selectedId = c.GetID();
                    String selectedItem = c.GetName();

                    List<Asset> listAssetByCategoryName = editRequestAdminActivityModelView.GetAllAssetByCategory(selectedItem);
                    AssetRequestAdapter(listAssetByCategoryName);

                    editor.putString("Category_id",selectedId);
                    editor.putString("Assignment_id",assignmentID);
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

    public void StateRequestAdapter(){
        List<String> listState = new ArrayList<>();
        listState.add("Pending");
        listState.add("Assigned");
        listState.add("Recovered");
        listState.add("Recovering");

        String stateRequest = dataBundle.getString("state");
        for(int i=0;i<listState.size();i++){
            if(listState.get(i).equalsIgnoreCase(stateRequest)){
                listState.remove(i);
                break;
            }
        }

        listState.add(0,stateRequest);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listState);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStateRequest.setAdapter(arrayAdapter);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        spinnerStateRequest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String SelectedItem = listState.get(position);

                editor.putString("State",SelectedItem);
                editor.commit();

                Toast.makeText(getApplicationContext(), "Id đã chọn: " + SelectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }

    public void Pick_Date_Birth(){
        Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                edt_DateOfReturning_Layout.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year,month,date);
        datePickerDialog.show();
    }

}
