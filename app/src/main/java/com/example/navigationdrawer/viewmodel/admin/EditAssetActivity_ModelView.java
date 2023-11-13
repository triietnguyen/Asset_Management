package com.example.navigationdrawer.viewmodel.admin;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.navigationdrawer.BR;
import com.example.navigationdrawer.model.Asset;
import com.example.navigationdrawer.model.Assignment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditAssetActivity_ModelView extends BaseObservable {
    public String categoryName_txt, assetName_txt;

    @Bindable
    public String getCategoryName_txt() {
        return categoryName_txt;
    }

    public void setCategoryName_txt(String categoryName_txt) {
        this.categoryName_txt = categoryName_txt;
        notifyPropertyChanged(BR.categoryName_txt);
    }

    @Bindable
    public String getAssetName_txt() {
        return assetName_txt;
    }

    public void setAssetName_txt(String assetName_txt) {
        this.assetName_txt = assetName_txt;
        notifyPropertyChanged(BR.assetName_txt);
    }

    public void OnClickButton(Context context) {
        Asset asset = new Asset();
        SharedPreferences sharedPreferences = context.getSharedPreferences("EditAsset", Context.MODE_PRIVATE);
        String assetID = sharedPreferences.getString("Asset_id", "");
        String assetName = sharedPreferences.getString("Asset_Name", getAssetName_txt());
        String state = sharedPreferences.getString("Status", "");
        switch (state) {
            case "Available":
                state = "1";
                break;
            default:
                state = "0";
                break;
        }
        Toast.makeText(context, "Sua thanh cong", Toast.LENGTH_LONG).show();
        asset.UpdateEditAsset(assetID, assetName, state);

        Intent returnIntent = new Intent();
        ((Activity) context).setResult(RESULT_OK, returnIntent);
        ((Activity) context).finish();
    }
}

