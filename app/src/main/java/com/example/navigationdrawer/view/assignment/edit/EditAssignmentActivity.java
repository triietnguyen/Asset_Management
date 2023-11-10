package com.example.navigationdrawer.view.assignment.edit;

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
import com.example.navigationdrawer.databinding.ActivityEditAssignmentAdminBinding;
import com.example.navigationdrawer.databinding.ActivityEditRequestAdminBinding;
import com.example.navigationdrawer.view.assignment.AssignmentAdminActivity;
import com.example.navigationdrawer.viewmodel.admin.EditAssignmentActivity_ModelView;
import com.example.navigationdrawer.viewmodel.admin.EditRequestAdminActivity_ModelView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditAssignmentActivity extends AppCompatActivity {
    Button img_CancelPage;
    Spinner state_spinner;
    EditText edt_DateOfReturning_Layout;
    SharedPreferences sharedPreferences;
    Bundle dataBundle;
    EditAssignmentActivity_ModelView editAssignmentActivity_modelView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("EditAssignment", Context.MODE_PRIVATE);
        dataBundle = getIntent().getExtras();

        ActivityEditAssignmentAdminBinding _binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_assignment_admin);
        editAssignmentActivity_modelView = new EditAssignmentActivity_ModelView();


        _binding.setEditAssignmentActivityModelView(editAssignmentActivity_modelView);

        AnhXa();
        HandleComponent();
        StateRequestAdapter();

    }

    public void AnhXa(){
        img_CancelPage = findViewById(R.id.img_CancelPage);
        state_spinner = findViewById(R.id.state_spinner);
        edt_DateOfReturning_Layout = findViewById(R.id.edt_DateOfReturning_Layout);

    }

    public void HandleComponent(){
        img_CancelPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditAssignmentActivity.this, AssignmentAdminActivity.class);
                startActivity(intent);
                finish();
            }
        });

        String categoryName = dataBundle.getString("categoryName");
        String assetName = dataBundle.getString("assetName");
        String startDate = dataBundle.getString("startDate");
        String endDate = dataBundle.getString("endDate");

        editAssignmentActivity_modelView.setAssetName_txt(assetName);
        editAssignmentActivity_modelView.setCurrentDate_edt(startDate);
        editAssignmentActivity_modelView.setReturnDate_txt(endDate);
        editAssignmentActivity_modelView.setCategoryName_txt(categoryName);

        edt_DateOfReturning_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pick_Date_Birth();
            }
        });

    }

    public void StateRequestAdapter(){
        List<String> listState = new ArrayList<>();
        listState.add("Assigned");
        listState.add("Recovered");
        listState.add("Recovering");

        String assignmentID = dataBundle.getString("id");

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
        state_spinner.setAdapter(arrayAdapter);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        state_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String SelectedItem = listState.get(position);

                editor.putString("State",SelectedItem);
                editor.putString("Assignment_id",assignmentID);
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
