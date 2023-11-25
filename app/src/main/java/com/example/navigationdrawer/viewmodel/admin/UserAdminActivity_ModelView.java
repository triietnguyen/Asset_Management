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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.navigationdrawer.model.Assignment;
import com.example.navigationdrawer.model.Gender;
import com.example.navigationdrawer.model.Role;
import com.example.navigationdrawer.model.User;

public class UserAdminActivity_ModelView extends BaseObservable {
    private String date,email,password,fullname,address,birthday,phone;
    private String searchStr;
    @Bindable
    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
        notifyPropertyChanged(BR.searchStr);
    }

    @Bindable
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
        notifyPropertyChanged(BR.birthday);
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
    @Bindable
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
        notifyPropertyChanged(BR.fullname);
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
        notifyPropertyChanged(BR.address);
    }

    @Bindable
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
        notifyPropertyChanged(BR.date);
    }

    public List<User> GetAllUser(){
        User user = new User();
        List<User> listUser = user.GetAllUser();
        return listUser;

    }

    public List<Gender> GetAllGender(){
        Gender g = new Gender();
        return g.GetAllGender();
    }

    public List<Role> GetAllRole(){
        Role role = new Role();
        List<Role> listRole = role.GetAllRole();
        return listRole;

    }
    public String Pick_Date_Birth(){
        if(birthday == null){
            return "0";
        }else{
            Calendar calendar = Calendar.getInstance();
            int dateCalendar = calendar.get(Calendar.DATE);
            int monthCalendar = calendar.get(Calendar.MONTH);
            int yearCalendar = calendar.get(Calendar.YEAR);
            calendar.set(yearCalendar,monthCalendar,dateCalendar);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate_Str = simpleDateFormat.format(calendar.getTime());
            birthday = currentDate_Str;
            return birthday;
        }


    }
    public void OnClickSaveButton(Context context) {
        if(date == null){
            return;
        }
        Calendar calendar = Calendar.getInstance();
        int dateCalendar = calendar.get(Calendar.DATE);
        int monthCalendar = calendar.get(Calendar.MONTH);
        int yearCalendar = calendar.get(Calendar.YEAR);
        calendar.set(yearCalendar,monthCalendar,dateCalendar);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate_Str = simpleDateFormat.format(calendar.getTime());

        Date currentDate = null;
        try {
            currentDate = simpleDateFormat.parse(currentDate_Str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date dateOfUser = null;
        try {
            dateOfUser = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if(dateOfUser.compareTo(currentDate) <= 0){
            SharedPreferences sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE);
            email = sharedPreferences.getString("Email",getEmail());
            password = sharedPreferences.getString("Password",getPassword());
            fullname = sharedPreferences.getString("Fullname",getFullname());
            address = sharedPreferences.getString("Address",getAddress());
            birthday = sharedPreferences.getString("Date_of_birth",getBirthday());
            String gender = sharedPreferences.getString("Gender","");
            String image = "content://com.android.providers.media.documents/document/image%3A37";
            String role = sharedPreferences.getString("Role_id","");
            phone = sharedPreferences.getString("Phone",getPhone());

            birthday = Pick_Date_Birth();
            User a = new User(null,email,password,fullname,address,birthday,gender,date,image,role,phone);
            a.AddUser();
            Intent returnIntent = new Intent();
            ((Activity)context).setResult(RESULT_OK, returnIntent);
            ((Activity)context).finish();
        }
        else if(dateOfUser.compareTo(currentDate) > 0){
            Toast.makeText(context, "Ngay Thang Nam Phai be hon hien tai ", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    public List<User> GetUsersBySearch(String search, String filter){
        User user = new User();
        List<User> listUsers = new ArrayList<>();

        if(filter.equalsIgnoreCase("All") && search.equalsIgnoreCase("")){
            listUsers = user.GetAllUser();
        }
        else{
            switch (filter){
                case "All": filter = "All";break;
                case "ID": filter ="User_id";break;
                case "Name": filter ="Fullname";break;
                case "Joined Date": filter ="Joined_Date";break;
                default:break;
            }
            listUsers = user.GetUserBySearch(search,filter);
        }
        return listUsers;
    }

}
