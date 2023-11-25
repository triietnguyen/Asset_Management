package com.example.navigationdrawer.view.report;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.model.Report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ReportAdminAdapter extends RecyclerView.Adapter<ReportAdminAdapter.ViewHolder> {

    Context context;
    List<Report> listReport;

    public ReportAdminAdapter(Context context, List<Report> listReport) {
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
        if (listReport != null && listReport.size() > 0) {
            Report report = listReport.get(position);
            holder.txt_category_id_layout.setText(report.getCategory_id());
            holder.txt_total_layout.setText(report.getTotal());
            holder.txt_assigned_layout.setText(report.getAssigned());
            holder.txt_available_layout.setText(report.getAvailable());
            holder.txt_not_available_layout.setText(report.getNot_available());
            holder.txt_waiting_layout.setText(report.getWaiting());
        } else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return listReport.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_category_id_layout, txt_total_layout, txt_assigned_layout, txt_available_layout, txt_not_available_layout, txt_waiting_layout;
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
    public void createExcelFile() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Report Data");
        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Category", "Total", "Assigned", "Available", "Not Available", "Waiting for recycling"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Populate data
        for (int i = 0; i < listReport.size(); i++) {
            Report report = listReport.get(i);
            Row dataRow = sheet.createRow(i + 1);
            dataRow.createCell(0).setCellValue(report.getCategory_id());
            dataRow.createCell(1).setCellValue(report.getTotal());
            dataRow.createCell(2).setCellValue(report.getAssigned());
            dataRow.createCell(3).setCellValue(report.getAvailable());
            dataRow.createCell(4).setCellValue(report.getNot_available());
            dataRow.createCell(5).setCellValue(report.getWaiting());
        }

        // Save the workbook to a file
        try {
            String filePath = Environment.getExternalStorageDirectory().getPath() + "/ReportData.xlsx";
            FileOutputStream fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
            fileOut.close();
            Toast.makeText(context, "Excel file created successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error creating Excel file", Toast.LENGTH_SHORT).show();
        }
    }
}
