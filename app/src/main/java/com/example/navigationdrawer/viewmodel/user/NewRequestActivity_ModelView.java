package com.example.navigationdrawer.viewmodel.user;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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

public class NewRequestActivity_ModelView extends BaseObservable {
    String description;

    @Bindable
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    public List<Category> GetAllCategory(){
        Category c = new Category();
        return c.GetAllCategory();
    }
    public List<Asset> GetAllAssetByCategory(String categoryName){
        Asset a = new Asset();
        return a.GetAssetByCategoryName(categoryName);
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
            String assetID = sharedPreferences.getString("Asset_id","");

            if (assetID.equalsIgnoreCase("")) {
                return;
            }

            User u = new User();
            String userID = u.GetNameUserByEmail(MyApplication.getInstance().GetSharedData());

            Assignment a = new Assignment(null,assetID,null,categoryID,userID,null,currentDate_Str,null,description,"0");
            a.AddAssignment();

            ((Activity) context).finish();

    }
}
