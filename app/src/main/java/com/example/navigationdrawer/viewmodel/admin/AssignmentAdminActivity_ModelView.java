package com.example.navigationdrawer.viewmodel.admin;

import android.content.Context;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.example.navigationdrawer.BR;
import com.example.navigationdrawer.model.Assignment;

public class AssignmentAdminActivity_ModelView extends BaseObservable {

    public String searchStr;

    @Bindable
    public String getSearchStr() {
        return searchStr;
    }
    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
        notifyPropertyChanged(BR.searchStr);
    }
    public List<Assignment> GetAllAssignment(){
        Assignment assignment = new Assignment();
        List<Assignment> listAssign = assignment.GetAllAssignmentAvailable();
        return listAssign;
    }

    public List<Assignment> GetAssignmentBySearch(String search,String filter){
        Assignment assignment = new Assignment();
        List<Assignment> listAssign = new ArrayList<>();

        if(filter.equalsIgnoreCase("all") && search.equalsIgnoreCase("")){
            listAssign = assignment.GetAllAssignmentAvailable();
        }
        else{
            switch (filter){
                case "all": filter = "all";break;
                case "ID": filter ="Assignment_id";break;
                case "Asset Code": filter ="Asset_id";break;
                case "Asset Name": filter ="Asset_Name";break;
                case "Category": filter ="Category_Name";break;
                case "Assigned To": filter ="u_user.Fullname";break;
                case "Assigned By": filter ="u_admin.Fullname";break;
                case "Assigned Date": filter ="StartDate";break;
                case "State": filter ="Status";break;
                default:break;
            }
            listAssign = assignment.GetAssignmentsAdminBySearch(search,filter);
        }
        return listAssign;
    }



}
