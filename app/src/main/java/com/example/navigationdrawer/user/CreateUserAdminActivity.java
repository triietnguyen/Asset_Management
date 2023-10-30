package com.example.navigationdrawer.user;

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
import com.example.navigationdrawer.databinding.ActivityNewStaffAdminBinding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Models.Gender;
import Models.Role;
import Models.SQLServer.SQLServer;
import ViewModels.Admin.UserAdminActivity_ModelView;

public class CreateUserAdminActivity extends AppCompatActivity {
    Button img_Cancel_Create;

    EditText edt_Joined_Date_Layout, edt_Date_of_birth_Layout;
    String image;
    Spinner gender_spinner, role_spinner;

    private Connection connect;
    List<String> listGenderName = new ArrayList<>();

    List<String> listRoleName = new ArrayList<>();

    SharedPreferences sharedPreferences;
    UserAdminActivity_ModelView userAdminActivityModelView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        ActivityNewStaffAdminBinding activityNewStaffAdminBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_staff_admin);
        userAdminActivityModelView = new UserAdminActivity_ModelView();
        activityNewStaffAdminBinding.setUserAdminActivityModelView(userAdminActivityModelView);
        AnhXa();
        Handle_Component();
        GenderUserAdapter();
        RoleUserAdapter();
    }
    void AnhXa(){
        img_Cancel_Create = (Button) findViewById(R.id.img_Cancel_Layout);
        edt_Joined_Date_Layout = (EditText) findViewById(R.id.edt_Joined_Date_Layout);
        edt_Date_of_birth_Layout = (EditText) findViewById(R.id.edt_Date_of_birth_Layout);
        gender_spinner = (Spinner) findViewById(R.id.gender_spinner) ;
        role_spinner = (Spinner) findViewById(R.id.role_spinner) ;
    }
    void Handle_Component(){
        img_Cancel_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateUserAdminActivity.this, UserAdminActivity.class);
                startActivity(intent);
                finish();
            }
        });

        edt_Joined_Date_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pick_Joined_Date();
            }
        });

        edt_Date_of_birth_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pick_Date_Birth();
            }
        });
    }

    private void Pick_Joined_Date(){
        Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                edt_Joined_Date_Layout.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year,month,date);
        datePickerDialog.show();
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
                edt_Date_of_birth_Layout.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year,month,date);
        datePickerDialog.show();
    }

    public void GenderUserAdapter(){

        List<Gender> listGender = userAdminActivityModelView.GetAllGender();
        listGenderName.clear();
        for(Gender c : listGender){
            listGenderName.add(c.getName());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listGenderName);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender_spinner.setAdapter(arrayAdapter);
        gender_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Sự kiện xảy ra khi một mục được chọn
                SharedPreferences.Editor editor = sharedPreferences.edit();

                Gender c = listGender.get(position);
                String selectedId = c.getId();


                editor.putString("Gender",selectedId);
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

    public void RoleUserAdapter(){

        List<Role> listRole = userAdminActivityModelView.GetAllRole();
        listRoleName.clear();
        for(Role c : listRole){
            listRoleName.add(c.getName());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listRoleName);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role_spinner.setAdapter(arrayAdapter);
        role_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Sự kiện xảy ra khi một mục được chọn
                SharedPreferences.Editor editor = sharedPreferences.edit();

                Role c = listRole.get(position);
                String selectedId = c.getId();

                editor.putString("Role_id",selectedId);
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
