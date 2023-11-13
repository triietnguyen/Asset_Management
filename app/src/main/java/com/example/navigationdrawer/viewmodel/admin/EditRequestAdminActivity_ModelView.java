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
import com.example.navigationdrawer.model.Asset;
import com.example.navigationdrawer.model.Assignment;
import com.example.navigationdrawer.model.Category;
import com.example.navigationdrawer.model.MyApplication;
import com.example.navigationdrawer.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EditRequestAdminActivity_ModelView extends BaseObservable {
    String edt_currentDate,edt_returnDate;
    @Bindable
    public String getEdt_currentDate() {
        return edt_currentDate;
    }
    public void setEdt_currentDate(String edt_currentDate) {
        this.edt_currentDate = edt_currentDate;
        notifyPropertyChanged(BR.edt_currentDate);
    }

    @Bindable
    public String getEdt_returnDate() {
        return edt_returnDate;
    }
    public void setEdt_returnDate(String edt_returnDate) {
        this.edt_returnDate = edt_returnDate;
        notifyPropertyChanged(BR.edt_returnDate);
    }

    public List<Category> GetAllCategory(){
        Category c = new Category();
        return c.GetAllCategory();
    }

    public List<Asset> GetAllAssetByCategory(String categoryName){
        Asset a = new Asset();
        return a.GetAssetByCategoryName(categoryName);
    }

    public void OnClickButton(Context context){
        Assignment assigment = new Assignment();
        Date d1,d2;
        SharedPreferences sharedPreferences = context.getSharedPreferences("RequestList", Context.MODE_PRIVATE);
        User u = new User();
        String adminID = u.GetNameUserByEmail(MyApplication.getInstance().GetSharedData());
        String currentDate = edt_currentDate;
        String returnDate = edt_returnDate;
        String assignmentID = sharedPreferences.getString("Assignment_id","");
        String categoryID = sharedPreferences.getString("Category_id","");
        String assetID = sharedPreferences.getString("Asset_id","");
        String state = sharedPreferences.getString("State","");

        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");


        if(state.equalsIgnoreCase("Pending")){
            Toast.makeText(context,"vui long thay doi State",Toast.LENGTH_LONG).show();
            return;
        }
        else {
            if (assetID.equalsIgnoreCase("")) {
                Toast.makeText(context, "Asset khong co de thay doi", Toast.LENGTH_LONG).show();
                return;
            } else {
                switch (state) {
                    case "Assigned":
                        state = "1";
                        break;
                    case "Recovered":
                        state = "2";
                        break;
                    case "Recovering":
                        state = "3";
                        break;
                    default:
                        break;
                }
                if (returnDate == null) {
                    assigment.UpdateRequestAssignment(adminID.trim(), assignmentID.trim(), assetID.trim(), categoryID.trim(), currentDate, null, state);

                    Intent returnIntent = new Intent();
                    ((Activity) context).setResult(RESULT_OK, returnIntent);
                    ((Activity) context).finish();
                } else {
                    try {
                        d1 = sdformat.parse(currentDate);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        d2 = sdformat.parse(returnDate);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }

                    if (d1.compareTo(d2) > 0) {
                        Toast.makeText(context, "Ngay tra khong duoc be hon ngay hien tai", Toast.LENGTH_LONG).show();

                    } else if (d1.compareTo(d2) < 0) {
                        Toast.makeText(context, "Sua thanh cong", Toast.LENGTH_LONG).show();

                        assigment.UpdateRequestAssignment(adminID.trim(), assignmentID.trim(), assetID.trim(), categoryID.trim(), currentDate, returnDate, state);

                        Intent returnIntent = new Intent();
                        ((Activity) context).setResult(RESULT_OK, returnIntent);
                        ((Activity) context).finish();
                    } else if (d1.compareTo(d2) == 0) {
                        Toast.makeText(context, "Ngay tra khong duoc bang ngay hien tai", Toast.LENGTH_LONG).show();
                    }


                }

            }
        }


    }
}
