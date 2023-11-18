package com.example.navigationdrawer.viewmodel.admin;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.navigationdrawer.BR;
import com.example.navigationdrawer.model.Asset;
import com.example.navigationdrawer.model.Assignment;
import com.example.navigationdrawer.model.MyApplication;
import com.example.navigationdrawer.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditUserActivity_ModelView extends BaseObservable {
    public String joined_date, name, date_birth, address, phone, email, password;

    @Bindable
    public String getJoined_date() {
        return joined_date;
    }

    public void setJoined_date(String joined_date) {
        this.joined_date = joined_date;
        notifyPropertyChanged(BR.joined_date);
    }
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
    @Bindable
    public String getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(String date_birth) {
        this.date_birth = date_birth;
        notifyPropertyChanged(BR.date_birth);
    }
    @Bindable
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
    }
    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }
    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }
    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }


    public void OnClickUpdateButton(Context context){
            User a = new User();
            SharedPreferences sharedPreferences = context.getSharedPreferences("EditUser", Context.MODE_PRIVATE);
            String user_id = sharedPreferences.getString("User_id","");
            String email = sharedPreferences.getString("Email",getEmail());
            String password = sharedPreferences.getString("Password",getPassword());
            String name = sharedPreferences.getString("Fullname",getName());
            String address = sharedPreferences.getString("Address",getAddress());
            String date_birth = sharedPreferences.getString("Date_of_birth",getDate_birth());
            String joined_date = sharedPreferences.getString("Joined_Date",getJoined_date());
            String phone = sharedPreferences.getString("Phone",getPhone());
            String gender = sharedPreferences.getString("Gender_id","");
            String role = sharedPreferences.getString("Role_id","");

            a.UpdateEditUser(user_id, email, password, name, address, date_birth, gender,joined_date, role, phone);
            Intent returnIntent = new Intent();
            ((Activity)context).setResult(RESULT_OK, returnIntent);
            ((Activity)context).finish();

    }
}

