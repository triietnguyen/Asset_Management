package com.example.navigationdrawer.view.report;

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
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

            // Example: Export to Excel when an item is clicked
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exportToExcel("YourExcelFileName.xlsx");
                }
            });
        }
    }

    public void exportToExcel(String fileName) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Report Data");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Category ID");
        headerRow.createCell(1).setCellValue("Total");
        headerRow.createCell(2).setCellValue("Assigned");
        headerRow.createCell(3).setCellValue("Available");
        headerRow.createCell(4).setCellValue("Not Available");
        headerRow.createCell(5).setCellValue("Waiting");

        // Add data rows
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

        // Save the workbook to external storage
        try (FileOutputStream fileOut = new FileOutputStream(Environment.getExternalStorageDirectory() + "/" + fileName)) {
            workbook.write(fileOut);
            Toast.makeText(context, "Exported to Excel", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error exporting to Excel", Toast.LENGTH_SHORT).show();
        }
    }
}
