package com.example.navigationdrawer.view.report;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.model.Asset;
import com.example.navigationdrawer.model.Report;
import com.example.navigationdrawer.view.asset.AssetAdminActivity;
import com.example.navigationdrawer.view.asset.edit.EditAssetActivity;

import java.util.List;

public class ReportAdminApdapter extends RecyclerView.Adapter<ReportAdminApdapter.ViewHolder> {

    Context context;
    List<Report> listReport;

    public ReportAdminApdapter(Context context, List<Report> listReport) {
        this.context = context;
        this.listReport = listReport;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_report_layout_admin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(listReport != null && listReport.size() > 0){
            Report report = listReport.get(position);
            holder.txt_category_id_layout.setText(report.getCategory_id());
            holder.txt_total_layout.setText(report.getTotal());
            holder.txt_assigned_layout.setText(report.getAssigned());
            holder.txt_available_layout.setText(report.getAvailable());
            holder.txt_not_available_layout.setText(report.getNot_available());
            holder.txt_waiting_layout.setText(report.getWaiting());
        }
        else{
            return;
        }
    }


    @Override
    public int getItemCount() {
        return listReport.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_category_id_layout,txt_total_layout,txt_assigned_layout,txt_available_layout,txt_not_available_layout,txt_waiting_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_category_id_layout = itemView.findViewById(R.id.txt_category_id_layout);
            txt_total_layout = itemView.findViewById(R.id.txt_total_layout);
            txt_assigned_layout = itemView.findViewById(R.id.txt_assigned_layout);
            txt_available_layout = itemView.findViewById(R.id.txt_available_layout);
            txt_not_available_layout = itemView.findViewById(R.id.txt_not_available_layout);
            txt_waiting_layout = itemView.findViewById(R.id.txt_waiting_layout);
        }
    }
}
