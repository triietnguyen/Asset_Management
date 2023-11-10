package com.example.navigationdrawer.viewmodel.user;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.ArrayList;
import java.util.List;

import com.example.navigationdrawer.BR;
import com.example.navigationdrawer.model.Assignment;

public class AssignmentActivity_ModelView extends BaseObservable {

    public String search;

    @Bindable
    public String getSearch() {
        return search;
    }
    public void setSearch(String search) {
        this.search = search;
        notifyPropertyChanged(BR.search);
    }


    public List<Assignment> GetAssignmentBySearch(String search,String filter,String userID){
        Assignment assignment = new Assignment();
        List<Assignment> listAssign = new ArrayList<>();

        if(filter.equalsIgnoreCase("all") && search.equalsIgnoreCase("")){
            listAssign = assignment.GetAllAssignmentByUser(userID);
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
            listAssign = assignment.GetAssignmentsUserBySearch(search,filter,userID);
        }
        return listAssign;
    }
}
