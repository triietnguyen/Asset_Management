package com.example.navigationdrawer;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import Models.Account;
import Models.MyApplication;

public class ChangePasswordForget_ModelView extends BaseObservable {
    private String newPassword;
    private String newPasswordVerify;

    @Bindable
    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        notifyPropertyChanged(BR.newPassword); // BR.email được sinh ra tự động bởi Data Binding
    }

    @Bindable
    public String getNewPasswordVerify() {
        return newPasswordVerify;
    }
    public void setNewPasswordVerify(String newPasswordVerify) {
        this.newPasswordVerify = newPasswordVerify;
        notifyPropertyChanged(BR.newPasswordVerify); // BR.email được sinh ra tự động bởi Data Binding
    }

    public void OnClickButton(Context context) {
        CheckPassword(context);
    }

    public void CheckPassword(Context context){
        String email = MyApplication.getInstance().GetSharedData();

        Account account = new Account(email, newPassword);
        if (account.IsValidPassword()) {
            String password = account.GetPassword();
            if (password.trim().equalsIgnoreCase(newPasswordVerify)) {

                boolean isFisnihed = account.IsUpdatePassword();
                if (isFisnihed) {
                    Toast.makeText(context, "Saved Successful", Toast.LENGTH_SHORT).show();
                    ((Activity) context).finish();
                }
                else{
                    Toast.makeText(context, "Saved is not Successful", Toast.LENGTH_SHORT).show();
                }
            }

        } else {
            Toast.makeText(context, "Password error, please try again ", Toast.LENGTH_SHORT).show();
        }
    }
}
