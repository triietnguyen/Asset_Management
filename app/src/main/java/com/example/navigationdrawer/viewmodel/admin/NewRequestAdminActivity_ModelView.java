package com.example.navigationdrawer.viewmodel.admin;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.navigationdrawer.BR;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.navigationdrawer.model.Asset;
import com.example.navigationdrawer.model.Assignment;
import com.example.navigationdrawer.model.Category;
import com.example.navigationdrawer.model.MyApplication;
import com.example.navigationdrawer.model.User;

public class NewRequestAdminActivity_ModelView extends BaseObservable {
    String date;

    @Bindable
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
        notifyPropertyChanged(BR.date);
    }

    public List<Category> GetAllCategory(){
       Category c = new Category();
       return c.GetAllCategory();
    }
    public List<Asset> GetAllAssetByCategory(String categoryName){
        Asset a = new Asset();
        return a.GetAssetByCategoryName(categoryName);
    }

    public List<User> GetAllUser(){
        User u = new User();
        return u.GetAllUser();
    }

    public void OnClickSaveButton(Context context) {
        Calendar calendar = Calendar.getInstance();

        int dateCalendar = calendar.get(Calendar.DATE);
        int monthCalendar = calendar.get(Calendar.MONTH);
        int yearCalendar = calendar.get(Calendar.YEAR);
        calendar.set(yearCalendar,monthCalendar,dateCalendar);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate_Str = simpleDateFormat.format(calendar.getTime());


        SharedPreferences sharedPreferences = context.getSharedPreferences("Assignment", Context.MODE_PRIVATE);
        String categoryID = sharedPreferences.getString("Category_id","");
        String userID = sharedPreferences.getString("User_id","");
        String assetID = sharedPreferences.getString("Asset_id","");
        Log.e("assetID",assetID);
        if (assetID.equalsIgnoreCase("")) {
            Toast.makeText(context, "Please choice asset", Toast.LENGTH_SHORT).show();
            return;
        }
        User u = new User();
        String adminID = u.GetNameUserByEmail(MyApplication.getInstance().GetSharedData());
        Assignment a = new Assignment(null,assetID,null,categoryID,userID,adminID,currentDate_Str,null,"","1");
        a.AddAssignment();
        Intent returnIntent = new Intent();
        ((Activity)context).setResult(RESULT_OK, returnIntent);
        ((Activity)context).finish();


    }
}
