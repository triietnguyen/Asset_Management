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
import com.example.navigationdrawer.model.Assignment;
import com.example.navigationdrawer.model.MyApplication;
import com.example.navigationdrawer.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditAssignmentActivity_ModelView extends BaseObservable {
    public String currentDate_edt;
    public String returnDate_txt;
    public String categoryName_txt;
    public String assetName_txt;
    @Bindable
    public String getCurrentDate_edt() {
        return currentDate_edt;
    }
    public void setCurrentDate_edt(String currentDate_edt) {
        this.currentDate_edt = currentDate_edt;
        notifyPropertyChanged(BR.currentDate_edt);
    }
    @Bindable
    public String getReturnDate_txt() {
        return returnDate_txt;
    }
    public void setReturnDate_txt(String returnDate_txt) {
        this.returnDate_txt = returnDate_txt;
        notifyPropertyChanged(BR.returnDate_txt);
    }
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

    public void OnClickButton(Context context){
        Assignment assignment = new Assignment();
        SharedPreferences sharedPreferences = context.getSharedPreferences("EditAssignment", Context.MODE_PRIVATE);
        Date d1,d2;
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");

        String assignmentID = sharedPreferences.getString("Assignment_id","");
        String state = sharedPreferences.getString("State","");
        String currentDate = currentDate_edt;
        String returnDate = returnDate_txt;

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

        if(returnDate == null){
            assignment.UpdateEditAssignment(assignmentID,null,state);

            Intent returnIntent = new Intent();
            ((Activity) context).setResult(RESULT_OK, returnIntent);
            ((Activity) context).finish();
        }
        else{
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
                assignment.UpdateEditAssignment(assignmentID,returnDate,state);

                Intent returnIntent = new Intent();
                ((Activity) context).setResult(RESULT_OK, returnIntent);
                ((Activity) context).finish();
            } else if (d1.compareTo(d2) == 0) {
                Toast.makeText(context, "Ngay tra khong duoc bang ngay hien tai", Toast.LENGTH_LONG).show();
            }
        }
    }


}
