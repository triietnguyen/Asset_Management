package com.example.navigationdrawer.viewmodel.admin;
import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.navigationdrawer.BR;

import java.util.ArrayList;
import java.util.List;

import com.example.navigationdrawer.model.Asset;
import com.example.navigationdrawer.model.Assignment;
import com.example.navigationdrawer.model.Category;

public class AssetAdminActivity_ModelView extends BaseObservable {

    SharedPreferences sharedPreferences;

    private String  asset_name , asset_category, status;
    public String searchStr;

    @Bindable
    public String getSearchStr() {
        return searchStr;
    }
    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
        notifyPropertyChanged(BR.searchStr);
    }

    @Bindable
    public String getAsset_name() {
        return asset_name;
    }

    public void setAsset_name(String asset_name) {
        this.asset_name = asset_name;
        notifyPropertyChanged(BR.asset_name);
    }
    @Bindable
    public String getAsset_category() {
        return asset_category;
    }

    public void setAsset_category(String asset_category) {
        this.asset_category = asset_category;
        notifyPropertyChanged(BR.asset_category);
    }
    @Bindable
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        notifyPropertyChanged(BR.status);
    }

    public List<Asset> GetAllAssets(){
        Asset asset = new Asset();
        List<Asset> listUser = asset.GetAllAssets();
        return listUser;
    }

    public List<Category> GetAllCategory(){
        Category c = new Category();
        return c.GetAllCategory();
    }

    public List<Asset> GetAssetBySearch(String search, String filter, String status){
        Asset asset = new Asset();
        List<Asset> listAsset = new ArrayList<>();

        if(filter.equalsIgnoreCase("All") && search.equalsIgnoreCase("") && status.equalsIgnoreCase("All")){
            listAsset = asset.GetAllAssets();
        }
        else{
            switch (filter){
                case "All": filter = "All";break;
                case "Asset Code": filter ="Asset_id";break;
                case "Asset Name": filter ="Asset_Name";break;
                case "Category": filter ="Category_Name";break;
                default:break;
            }
            switch (status){
                case "All": status = "All";break;
                case "Available": status ="1";break;
                case "Not Available": status ="0";break;
                default:break;
            }
            listAsset = asset.GetAssetsAdminBySearch(search,filter,status);
        }
        return listAsset;
    }
    public void OnClickSaveButton(Context context) {
            sharedPreferences = context.getSharedPreferences("Asset", Context.MODE_PRIVATE);
            asset_name = sharedPreferences.getString("Asset_Name",getAsset_name());
            asset_category = sharedPreferences.getString("Category_id","");
            status = sharedPreferences.getString("Status","");

            Asset a = new Asset(null,getAsset_name().toString(),getAsset_category().toString(),getStatus().toString());
            a.AddAsset();
            Intent returnIntent = new Intent();
            ((Activity)context).setResult(RESULT_OK, returnIntent);
            ((Activity)context).finish();

        }
    }

