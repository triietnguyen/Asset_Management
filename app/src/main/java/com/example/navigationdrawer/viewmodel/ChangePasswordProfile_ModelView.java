package com.example.navigationdrawer.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.navigationdrawer.BR;

import com.example.navigationdrawer.model.Account;
import com.example.navigationdrawer.model.MyApplication;

public class ChangePasswordProfile_ModelView extends BaseObservable {
    private String oldPassword;
    private String newPassword;
    private String newVerifyPassword;
    @Bindable
    public String getOldPassword() {
        return oldPassword;
    }
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        notifyPropertyChanged(BR.oldPassword); // BR.email được sinh ra tự động bởi Data Binding
    }

    @Bindable
    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        notifyPropertyChanged(BR.newPassword); // BR.email được sinh ra tự động bởi Data Binding
    }

    @Bindable
    public String getNewVerifyPassword() {
        return newVerifyPassword;
    }
    public void setNewVerifyPassword(String newVerifyPassword) {
        this.newVerifyPassword = newVerifyPassword;
        notifyPropertyChanged(BR.newVerifyPassword); // BR.email được sinh ra tự động bởi Data Binding
    }

    public void OnClickButton(Context context){
        String email = MyApplication.getInstance().GetSharedData();
        if(TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(oldPassword) || TextUtils.isEmpty(newVerifyPassword)){
            return;
        }

        Account account = new Account(email,oldPassword.trim());
        if(account.IsCheckValidPassword()){
            boolean isValid = IsValidPassword();
            if(isValid && newPassword.trim().equalsIgnoreCase(newVerifyPassword.trim())){
                account.SetPassword(newPassword);
                if(account.IsUpdatePassword()){
                    Toast.makeText(context, "Sua mat khau thanh cong", Toast.LENGTH_SHORT).show();
                    ((Activity) context).finish();
                }
                else{
                    Toast.makeText(context, "Mat khau cua ban khong trung khop ", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(context, "Mat khau cua ban bi sai ", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(context, "Mat khau phai lon hon 6 ki tu ", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean IsValidPassword(){
        return !TextUtils.isEmpty(newPassword) && newPassword.length() >=6;
    }




}
