package Models;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.SQLServer.SQLServer;

public class Assignment {

    String id, category;

    String asset_code, asset_name,assigned_to,assigned_by, assigned_date;

    String status;

    private Connection connect;

    public Assignment(String id, String asset_code, String asset_name, String category, String assigned_to, String assigned_by, String assigned_date, String status) {
        this.id = id;
        this.asset_code = asset_code;
        this.category = category;
        this.asset_name = asset_name;
        this.assigned_to = assigned_to;
        this.assigned_by = assigned_by;
        this.assigned_date = assigned_date;
        this.status = status;
    }

    public Assignment(){

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAsset_code() {
        return asset_code;
    }

    public void setAsset_code(String asset_code) {
        this.asset_code = asset_code;
    }

    public String getAsset_name() {
        return asset_name;
    }

    public void setAsset_name(String asset_name) {
        this.asset_name = asset_name;
    }

    public String getAssigned_to() {
        return assigned_to;
    }

    public void setAssigned_to(String assigned_to) {
        this.assigned_to = assigned_to;
    }

    public String getAssigned_by() {
        return assigned_by;
    }

    public void setAssigned_by(String assigned_by) {
        this.assigned_by = assigned_by;
    }

    public String getAssigned_date() {
        return assigned_date;
    }

    public void setAssigned_date(String assigned_date) {
        this.assigned_date = assigned_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Assignment> GetAllAssignment(){
        List<Assignment> listAssignment = new ArrayList<>();
        try{
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if(connect != null){
                String query = "SELECT assign.[Id], a.[Asset_id], a.[Asset_Name], c.[Category_Name], " +
                        "u_user.Fullname AS UserFullName, u_admin.Fullname AS AdminFullName, assign.[Status], assign.[StartDate] " +
                        "FROM Assignment assign " +
                        "LEFT JOIN [User] u_admin ON assign.[Admin_id] = u_admin.[User_id] AND u_admin.[Role_id] = 1 " +
                        "LEFT JOIN [User] u_user ON assign.[User_id] = u_user.[User_id] AND u_user.[Role_id] = 2 " +
                        "INNER JOIN [Asset] a ON assign.[Asset_id] = a.[Asset_id] " +
                        "INNER JOIN [Category] c ON assign.[Category_id] = c.[Category_id]";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                while(rs.next()){
                    String assignmentID = rs.getString("Id");
                    String assetID = rs.getString("Asset_id");
                    String assetName = rs.getString("Asset_Name");
                    String categoryName = rs.getString("Category_Name");
                    String user = rs.getString("UserFullName");
                    String admin = rs.getString("AdminFullName");
                    String startDate = rs.getString("StartDate");
                    String status = rs.getString("Status");

                    if(status.equalsIgnoreCase("1")){
                        status = "Pending";
                    }

                    Assignment assignment = new Assignment(assignmentID,
                            assetID,
                            assetName,
                            categoryName,
                            user,
                            admin,
                            startDate,
                            status);

                    listAssignment.add(assignment);
                }
                return listAssignment;

            }
        }catch(Exception e){
            Log.e(e.getMessage(),"Log error");
        }
        return null;

    }
}
