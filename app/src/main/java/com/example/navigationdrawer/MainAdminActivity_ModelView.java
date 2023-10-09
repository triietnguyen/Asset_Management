package com.example.navigationdrawer;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.HashMap;
import java.util.Map;

import Models.Account;
import Models.MyApplication;

public class MainAdminActivity_ModelView extends BaseObservable {
    private String fullName;
    private String email;
    Map<String, String> userMap = new HashMap<>();

    @Bindable
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
        notifyPropertyChanged(BR.fullName);
    }

    @Bindable
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }
    public void GetData(){
        String emailDB,fullnameDb;

        String data = MyApplication.getInstance().GetSharedData();
        Account account = new Account(data);
        userMap = account.GetUserFromDB();

        emailDB = userMap.get("Email");
        fullnameDb = userMap.get("Fullname");

        this.setEmail(emailDB);
        this.setFullName(fullnameDb);
    }
}
