package ViewModels.Admin;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.navigationdrawer.BR;

import java.util.List;

import Models.Asset;
import Models.Assignment;
import Models.Category;
import Models.MyApplication;
import Models.User;

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
        return u.GetUserAdapter();
    }

    public void OnClickSaveButton(Context context){
        if(date == null){
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("Assignment", Context.MODE_PRIVATE);
        String categoryID = sharedPreferences.getString("Category_id","");
        String userID = sharedPreferences.getString("User_id","");
        String assetID = sharedPreferences.getString("Asset_id","");

        User u = new User();
        String adminID = u.GetNameUserByEmail(MyApplication.getInstance().GetSharedData());

        Assignment a = new Assignment(assetID,categoryID,userID,adminID,date,"2023-02-03","1","1");
        a.AddAssignment();

        ((Activity) context).finish();

    }
}
