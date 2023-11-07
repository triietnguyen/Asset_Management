package com.example.navigationdrawer.viewmodel.admin;
import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.navigationdrawer.BR;

import java.util.List;

import com.example.navigationdrawer.model.Asset;
import com.example.navigationdrawer.model.Category;

public class AssetAdminActivity_ModelView extends BaseObservable {

    SharedPreferences sharedPreferences;

    private String  asset_name , asset_category, quantity, status;

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
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
        notifyPropertyChanged(BR.quantity);
    }
    @Bindable
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        notifyPropertyChanged(BR.status);
    }

    public List<Asset> GetAllAsset(){
        Asset asset = new Asset();
        List<Asset> listUser = asset.GetAllAsset();
        return listUser;
    }

    public List<Category> GetAllCategory(){
        Category c = new Category();
        return c.GetAllCategory();
    }
    public void OnClickSaveButton(Context context) {
            sharedPreferences = context.getSharedPreferences("Asset", Context.MODE_PRIVATE);
            asset_name = sharedPreferences.getString("Asset_Name",getAsset_name());
            asset_category = sharedPreferences.getString("Category_id","");
            quantity = sharedPreferences.getString("Quantity",getQuantity());
            status = sharedPreferences.getString("Status","");

            Asset a = new Asset(null,getAsset_name().toString(),getAsset_category().toString(),getQuantity().toString(),getStatus().toString());
            a.AddAsset();
            Intent returnIntent = new Intent();
            ((Activity)context).setResult(RESULT_OK, returnIntent);
            ((Activity)context).finish();

        }
    }

