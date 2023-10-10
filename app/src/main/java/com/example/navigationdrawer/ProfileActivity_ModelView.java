package com.example.navigationdrawer;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.HashMap;
import java.util.Map;

import Models.Account;
import Models.MyApplication;

public class ProfileActivity_ModelView extends BaseObservable {
    private String fullname;
    private String email;
    private String address;
    private String birthday;
    private String gender;
    private String phone;

    Map<String, String> userMap = new HashMap<>();

    @Bindable
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
        notifyPropertyChanged(BR.fullname);
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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
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
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
        notifyPropertyChanged(BR.gender);
    }

    @Bindable
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }

    public void GetData(){
        String emailDB,fullnameDb,addressDB,birthdayDB,genderDB,phoneDB;

        String data = MyApplication.getInstance().GetSharedData();
        Account account = new Account(data);
        userMap = account.GetUserFromDB();

        addressDB = userMap.get("Address");
        emailDB = userMap.get("Email");
        fullnameDb = userMap.get("Fullname");
        birthdayDB = userMap.get("Date_of_birth");
        genderDB = userMap.get("Gender");
        phoneDB = userMap.get("Phone");

        this.setEmail(emailDB);
        this.setFullname(fullnameDb);
        this.setAddress(addressDB);
        this.setBirthday(birthdayDB);
        this.setGender(genderDB);
        this.setPhone(phoneDB);
    }






}
