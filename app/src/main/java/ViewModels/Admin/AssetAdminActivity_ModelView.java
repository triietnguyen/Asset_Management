package ViewModels.Admin;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.navigationdrawer.BR;

import java.sql.Connection;
import java.util.List;

import Models.Asset;
import Models.Category;

public class AssetAdminActivity_ModelView extends BaseObservable {

    private Connection connect;
    SharedPreferences sharedPreferences;

    private String asset_id, asset_name , asset_category, quantity, status;

    @Bindable
    public String getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(String asset_id) {
        this.asset_id = asset_id;
        notifyPropertyChanged(BR.asset_id);
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
            SharedPreferences sharedPreferences = context.getSharedPreferences("Asset", Context.MODE_PRIVATE);
            asset_name = sharedPreferences.getString("Asset_Name",getAsset_name());
            asset_category = sharedPreferences.getString("Category_id","");
            quantity = sharedPreferences.getString("Quantity",getQuantity());
            status = sharedPreferences.getString("Status","");

            Asset a = new Asset(null,getAsset_name().toString(),getAsset_category().toString(),getQuantity().toString(),getStatus().toString());
            a.AddAsset();
            ((Activity) context).finish();
        }
    }

