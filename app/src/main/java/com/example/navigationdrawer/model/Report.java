package com.example.navigationdrawer.model;

import android.util.Log;

import com.example.navigationdrawer.model.SQLServer.SQLServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Report {
    private Connection connect;
    private String category_id, total, assigned, available, not_available, waiting;
    public Report(){}
    public Report(String category_id, String total, String assigned, String available, String not_available, String waiting) {
        this.category_id = category_id;
        this.total = total;
        this.assigned = assigned;
        this.available = available;
        this.not_available = not_available;
        this.waiting = waiting;
    }

    public Connection getConnect() {
        return connect;
    }

    public void setConnect(Connection connect) {
        this.connect = connect;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getNot_available() {
        return not_available;
    }

    public void setNot_available(String not_available) {
        this.not_available = not_available;
    }

    public String getWaiting() {
        return waiting;
    }

    public void setWaiting(String waiting) {
        this.waiting = waiting;
    }

    public List<Report> GetAllReports() {
        List<Report> listReport = new ArrayList<>();
        try {
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if (connect != null) {
                String query = "SELECT c.[Category_Name], " +
                        "COUNT(CASE WHEN ass.Status <> 2 THEN ass.[Category_id] END) AS Total, " +
                        "COUNT(asg.[Category_id]) AS Assigned, " +
                        "COUNT(CASE WHEN ass.[Status] = 1 THEN ass.[Category_id] END) AS Available, " +
                        "COUNT(CASE WHEN ass.[Status] = 0 THEN ass.[Category_id] END) AS 'Not Available', " +
                        "COUNT(CASE WHEN ass.[Status] = 2 THEN ass.[Category_id] END) AS 'Waiting for recycling' " +
                        "FROM [dbo].[Category] c " +
                        "LEFT JOIN [dbo].[Assignment] asg ON c.[Category_id] = asg.[Category_id] AND asg.[Status] = 1 " +
                        "LEFT JOIN [dbo].[Asset] ass ON c.[Category_id] = ass.[Category_id] " +
                        "GROUP BY c.[Category_Name] ";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    category_id = rs.getString("Category_Name");
                    total = rs.getString("Total");
                    assigned = rs.getString("Assigned");
                    available = rs.getString("Not Available");
                    not_available = rs.getString("Status");
                    waiting = rs.getString("Waiting for recycling");
                    Report r = new Report(category_id, total, assigned, available,not_available,waiting);
                    listReport.add((r));
                }
                return listReport;

            }
        } catch (Exception e) {
            Log.e(e.getMessage(), "Log error");
        }
        return null;
    }

}

