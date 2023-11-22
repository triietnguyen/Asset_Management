package com.example.navigationdrawer.viewmodel.admin;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.navigationdrawer.BR;
import com.example.navigationdrawer.model.Asset;
import com.example.navigationdrawer.model.Category;
import com.example.navigationdrawer.model.Report;

import java.util.ArrayList;
import java.util.List;

public class ReportAdminActivity_ModelView extends BaseObservable {

    SharedPreferences sharedPreferences;
    public List<Report> GetAllReports(){
        Report report = new Report();
        List<Report> listReport = report.GetAllReports();
        return listReport;
    }
}


