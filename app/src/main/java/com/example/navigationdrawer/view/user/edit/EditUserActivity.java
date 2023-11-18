package com.example.navigationdrawer.view.user.edit;

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
import com.example.navigationdrawer.databinding.ActivityEditStaffAdminBinding;
import com.example.navigationdrawer.databinding.ActivityStaffAdminBinding;
import com.example.navigationdrawer.model.Asset;
import com.example.navigationdrawer.model.Category;
import com.example.navigationdrawer.model.Gender;
import com.example.navigationdrawer.model.Role;
import com.example.navigationdrawer.model.User;
import com.example.navigationdrawer.view.asset.AssetAdminActivity;
import com.example.navigationdrawer.view.request.RequestAdminActivity;
import com.example.navigationdrawer.view.request.edit.EditRequestAdminActivity;
import com.example.navigationdrawer.view.user.UserAdminActivity;
import com.example.navigationdrawer.viewmodel.admin.EditAssetActivity_ModelView;
import com.example.navigationdrawer.viewmodel.admin.EditUserActivity_ModelView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditUserActivity extends AppCompatActivity {
    Button img_Cancel_Layout;
    Spinner gender_spinner, role_spinner;
    EditText edt_Date_of_birth_Layout;
    SharedPreferences sharedPreferences;
    Bundle dataBundle;
    EditUserActivity_ModelView editUserActivity_modelView;
    List<String> listGenderName = new ArrayList<>();
    List<String> listRoleName = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("EditUser", Context.MODE_PRIVATE);
        dataBundle = getIntent().getExtras();
        ActivityEditStaffAdminBinding _binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_staff_admin);
        editUserActivity_modelView = new EditUserActivity_ModelView();
        _binding.setEditUserActivityModelView(editUserActivity_modelView);
        AnhXa();
        HandleComponent();
        GenderUserAdapter();
        RoleUserAdapter();
    }

    public void AnhXa() {
        img_Cancel_Layout = findViewById(R.id.img_Cancel_Layout);
        edt_Date_of_birth_Layout = findViewById(R.id.edt_Date_of_birth_Layout);
        gender_spinner = findViewById(R.id.gender_spinner);
        role_spinner = findViewById(R.id.role_spinner);
    }

    public void Pick_Date_Birth() {
        Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                edt_Date_of_birth_Layout.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year, month, date);
        datePickerDialog.show();
    }

    public void HandleComponent() {
        img_Cancel_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditUserActivity.this, UserAdminActivity.class);
                startActivity(intent);
                finish();
            }
        });

        edt_Date_of_birth_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pick_Date_Birth();
            }
        });

        String joined_date = dataBundle.getString("Joined_Date");
        String user_name = dataBundle.getString("Fullname");
        String date_birth = dataBundle.getString("Date_of_birth");
        String address = dataBundle.getString("Address");
        String phone = dataBundle.getString("Phone");
        String email = dataBundle.getString("Email");
        String password = dataBundle.getString("Password");
        editUserActivity_modelView.setJoined_date(joined_date);
        editUserActivity_modelView.setName(user_name);
        editUserActivity_modelView.setDate_birth(date_birth);
        editUserActivity_modelView.setAddress(address);
        editUserActivity_modelView.setPhone(phone);
        editUserActivity_modelView.setEmail(email);
        editUserActivity_modelView.setPassword(password);
    }

    public void GenderUserAdapter() {

        Gender gender = new Gender();
        List<Gender> listGender = gender.GetAllGender();
        listGenderName.clear();
        String userID = dataBundle.getString("User_id");
        String genderID = dataBundle.getString("Gender_id");

        switch (genderID) {
            case "1":
                genderID = "Male";
                break;
            default:
                genderID = "Female";
                break;
        }
        for (Gender g : listGender) {
            if (!g.getName().equalsIgnoreCase(genderID)) {
                listGenderName.add(g.getName());
            }

        }
        listGenderName.add(0, genderID);


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listGenderName);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender_spinner.setAdapter(arrayAdapter);
        gender_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Sự kiện xảy ra khi một mục được chọn
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String g = listGenderName.get(position);
                switch (g){
                    case "Male": g = "1"; break;
                    case "Female": g ="2"; break;
                    default: break;
                }
                String selectedId = g;
                editor.putString("Gender_id", selectedId);
                editor.putString("User_id", userID);
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

    public void RoleUserAdapter() {
        Role role = new Role();
        List<Role> listRole = role.GetAllRole();
        listRoleName.clear();
        String userID = dataBundle.getString("User_id");
        String roleID = dataBundle.getString("Role_id");

        for (Role c : listRole) {
            if (!c.getName().equalsIgnoreCase(roleID)) {
                listRoleName.add(c.getName());
            }
        }
        listRoleName.add(0, roleID);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listRoleName);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role_spinner.setAdapter(arrayAdapter);
        role_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Sự kiện xảy ra khi một mục được chọn
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String c = listRoleName.get(position);
                switch (c){
                    case "User": c = "2"; break;
                    case "Admin": c ="1"; break;
                    default: break;
                }
                String selectedId = c;
                editor.putString("Role_id", selectedId);
                editor.putString("User_id", userID);
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
