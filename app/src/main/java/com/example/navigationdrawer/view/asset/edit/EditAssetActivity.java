package com.example.navigationdrawer.view.asset.edit;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.example.navigationdrawer.databinding.ActivityEditAssetAdminBinding;
import com.example.navigationdrawer.databinding.ActivityEditAssignmentAdminBinding;
import com.example.navigationdrawer.view.asset.AssetAdminActivity;
import com.example.navigationdrawer.view.assignment.AssignmentAdminActivity;
import com.example.navigationdrawer.viewmodel.admin.EditAssetActivity_ModelView;
import com.example.navigationdrawer.viewmodel.admin.EditAssignmentActivity_ModelView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditAssetActivity extends AppCompatActivity {
    Button img_CancelPage;
    Spinner state_spinner;
    EditText edt_DateOfReturning_Layout;
    SharedPreferences sharedPreferences;
    Bundle dataBundle;
    EditAssetActivity_ModelView editAssetActivity_modelView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("EditAsset", Context.MODE_PRIVATE);
        dataBundle = getIntent().getExtras();
        ActivityEditAssetAdminBinding _binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_asset_admin);
        editAssetActivity_modelView = new EditAssetActivity_ModelView();
        _binding.setEditAssetActivityModelView(editAssetActivity_modelView);
        AnhXa();
        HandleComponent();
        StateRequestAdapter();

    }

    public void AnhXa(){
        img_CancelPage = findViewById(R.id.img_Cancel_Edit);
        state_spinner = findViewById(R.id.asset_spinner_state);
    }

    public void HandleComponent(){
        img_CancelPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditAssetActivity.this, AssetAdminActivity.class);
                startActivity(intent);
                finish();
            }
        });

        String categoryName = dataBundle.getString("Category_id");
        String assetName = dataBundle.getString("Asset_Name");

        editAssetActivity_modelView.setAssetName_txt(assetName);
        editAssetActivity_modelView.setCategoryName_txt(categoryName);

    }

    public void StateRequestAdapter(){
        List<String> listState = new ArrayList<>();
        listState.add("Available");
        listState.add("Not Available");

        String assetID = dataBundle.getString("Asset_id");

        String stateRequest = dataBundle.getString("Status");
        for(int i=0;i<listState.size();i++){
            if(listState.get(i).equalsIgnoreCase(stateRequest)){
                listState.remove(i);
                break;
            }
        }
        listState.add(0,stateRequest);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listState);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state_spinner.setAdapter(arrayAdapter);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        state_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String SelectedItem = listState.get(position);

                editor.putString("Status",SelectedItem);
                editor.putString("Asset_id",assetID);
                editor.commit();

                Toast.makeText(getApplicationContext(), "Id đã chọn: " + SelectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }
}
