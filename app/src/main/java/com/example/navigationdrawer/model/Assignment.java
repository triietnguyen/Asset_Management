package com.example.navigationdrawer.model;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.navigationdrawer.model.SQLServer.SQLServer;

public class Assignment {

    String id, category;

    String asset_code, asset_name,assigned_to,assigned_by, assigned_date;

    String status;

    String endDate,description;

    private Connection connect;

    public Assignment(String id,String asset_code,String asset_name, String category, String assigned_to, String assigned_by, String assigned_date,String endDate,String description, String status) {
        this.id = id;
        this.asset_name = asset_name;
        this.asset_code = asset_code;
        this.category = category;
        this.assigned_to = assigned_to;
        this.assigned_by = assigned_by;
        this.assigned_date = assigned_date;
        this.endDate = endDate;
        this.description = description;
        this.status = status;
    }

    public Assignment(){

    }
    public String getAssignmentID(){ return id;}
    public String getEndDate(){ return endDate;}
    public String getDescription(){ return description;}
    public String getUser(){ return assigned_to;}

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

    public List<Assignment> GetAllAssignmentAvailable(){
        List<Assignment> listAssignment = new ArrayList<>();
        try{
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if(connect != null){
                String query = "SELECT assign.[Assignment_id], a.[Asset_id], a.[Asset_Name], assign.[EndDate], c.[Category_Name], " +
                        "u_user.Fullname AS UserFullName, u_admin.Fullname AS AdminFullName, assign.[Status], assign.[StartDate] " +
                        "FROM Assignment assign " +
                        "LEFT JOIN [User] u_admin ON assign.[Admin_id] = u_admin.[User_id] AND u_admin.[Role_id] = 1 " +
                        "LEFT JOIN [User] u_user ON assign.[User_id] = u_user.[User_id] AND u_user.[Role_id] = 2 " +
                        "INNER JOIN [Asset] a ON assign.[Asset_id] = a.[Asset_id] " +
                        "INNER JOIN [Category] c ON assign.[Category_id] = c.[Category_id] "+
                        "where assign.[Status] > 0";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                while(rs.next()){
                    String assetID = rs.getString("Asset_id");
                    String assignmentId = rs.getString("Assignment_id");
                    String assetName = rs.getString("Asset_Name");
                    String categoryName = rs.getString("Category_Name");
                    String user = rs.getString("UserFullName");
                    String admin = rs.getString("AdminFullName");
                    String startDate = rs.getString("StartDate");
                    String EndDate = rs.getString("EndDate");
                    String status = rs.getString("Status");

                    switch (status){
                        case "1":status = "Assigned";break;
                        case "2":status = "Returned";break;
                        case "3":status = "Returning";break;
                        default:break;
                    }

                    Assignment assignment = new Assignment(
                            assignmentId,
                            assetID,
                            assetName,
                            categoryName,
                            user,
                            admin,
                            startDate,
                            EndDate,
                            null,
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

    public List<Assignment> GetAssignmentsUserBySearch(String search, String filter,String userID){
        Log.e("search",search);
        Log.e("filter",filter);
        Log.e("userID",userID);
        List<Assignment> listAssignment = new ArrayList<>();
        try{
            String filterCondition = "";
            if (!"All".equals(filter)) {
                filterCondition = " AND "+ filter + " LIKE '" + search + "%'";
            }
            else{
                filterCondition = " AND " +
                        "(a.[Asset_id] LIKE '" + search + "%' OR " +
                        "a.[Asset_Name] LIKE '" + search + "%' OR " +
                        "c.[Category_Name] LIKE '" + search + "%' OR " +
                        "u_user.Fullname LIKE '" + search + "%' OR " +
                        "u_admin.Fullname LIKE '" + search + "%' OR " +
                        "assign.[Status] LIKE '" + search + "%' OR " +
                        "assign.[StartDate] LIKE '" + search + "%' OR " +
                        "assign.[EndDate] LIKE '" + search + "%' OR " +
                        "assign.[Assignment_id] LIKE '" + search + "%')";
            }
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if(connect != null){
                String query = "SELECT assign.[Assignment_id], a.[Asset_id], a.[Asset_Name], assign.[EndDate], c.[Category_Name], " +
                        "u_user.Fullname AS UserFullName, u_admin.Fullname AS AdminFullName, assign.[Status], assign.[StartDate] " +
                        "FROM Assignment assign " +
                        "LEFT JOIN [User] u_admin ON assign.[Admin_id] = u_admin.[User_id] AND u_admin.[Role_id] = 1 " +
                        "LEFT JOIN [User] u_user ON assign.[User_id] = u_user.[User_id] AND u_user.[Role_id] = 2 " +
                        "INNER JOIN [Asset] a ON assign.[Asset_id] = a.[Asset_id] " +
                        "INNER JOIN [Category] c ON assign.[Category_id] = c.[Category_id] " +
                        "WHERE assign.[Status] > 0 AND assign.[User_id] = ?" + filterCondition;
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, userID);
                ResultSet rs = preparedStatement.executeQuery();

                while(rs.next()){
                    String assetID = rs.getString("Asset_id");
                    String assignmentId = rs.getString("Assignment_id");
                    String assetName = rs.getString("Asset_Name");
                    String categoryName = rs.getString("Category_Name");
                    String user = rs.getString("UserFullName");
                    String admin = rs.getString("AdminFullName");
                    String startDate = rs.getString("StartDate");
                    String EndDate = rs.getString("EndDate");
                    String status = rs.getString("Status");

                    switch (status){
                        case "1":status = "Assigned";break;
                        case "2":status = "Returned";break;
                        case "3":status = "Returning";break;
                        default:break;
                    }

                    Assignment assignment = new Assignment(
                            assignmentId,
                            assetID,
                            assetName,
                            categoryName,
                            user,
                            admin,
                            startDate,
                            EndDate,
                            null,
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

    public List<Assignment> GetAssignmentsAdminBySearch(String search, String filter, String status_filter){
        Log.e("search",search);
        List<Assignment> listAssignment = new ArrayList<>();
        try{
            String filterCondition = "";
            if (!"All".equals(filter) && !"All".equals(status_filter)) {
                    filterCondition = " AND "+ filter + " LIKE '" + search + "%' AND assign.[Status] LIKE '" + status_filter + "'";
            }
            else{
                filterCondition = " AND " +
                        "(a.[Asset_id] LIKE '" + search + "%' AND assign.[Status] LIKE '" + status_filter + "' OR " +
                        "a.[Asset_Name] LIKE '" + search + "%' AND assign.[Status] LIKE '" + status_filter + "' OR " +
                        "c.[Category_Name] LIKE '" + search + "%' AND assign.[Status] LIKE '" + status_filter + "' OR " +
                        "u_user.Fullname LIKE '" + search + "%' AND assign.[Status] LIKE '" + status_filter + "' OR " +
                        "u_admin.Fullname LIKE '" + search + "%' AND assign.[Status] LIKE '" + status_filter + "' OR " +
                        "assign.[StartDate] LIKE '" + search + "%' AND assign.[Status] LIKE '" + status_filter + "' OR " +
                        "assign.[EndDate] LIKE '" + search + "%' AND assign.[Status] LIKE '" + status_filter + "' OR " +
                        "assign.[Assignment_id] LIKE '" + search + "%' AND assign.[Status] LIKE '" + status_filter + "')";
            }
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if(connect != null){
                String query = "SELECT assign.[Assignment_id], a.[Asset_id], a.[Asset_Name], assign.[EndDate], c.[Category_Name], " +
                        "u_user.Fullname AS UserFullName, u_admin.Fullname AS AdminFullName, assign.[Status], assign.[StartDate] " +
                        "FROM Assignment assign " +
                        "LEFT JOIN [User] u_admin ON assign.[Admin_id] = u_admin.[User_id] AND u_admin.[Role_id] = 1 " +
                        "LEFT JOIN [User] u_user ON assign.[User_id] = u_user.[User_id] AND u_user.[Role_id] = 2 " +
                        "INNER JOIN [Asset] a ON assign.[Asset_id] = a.[Asset_id] " +
                        "INNER JOIN [Category] c ON assign.[Category_id] = c.[Category_id] " +
                        "WHERE assign.[Status] > 0" + filterCondition;
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);

                while(rs.next()){
                    String assetID = rs.getString("Asset_id");
                    String assignmentId = rs.getString("Assignment_id");
                    String assetName = rs.getString("Asset_Name");
                    String categoryName = rs.getString("Category_Name");
                    String user = rs.getString("UserFullName");
                    String admin = rs.getString("AdminFullName");
                    String startDate = rs.getString("StartDate");
                    String EndDate = rs.getString("EndDate");
                    String status = rs.getString("Status");

                    switch (status){
                        case "1":status = "Assigned";break;
                        case "2":status = "Returned";break;
                        case "3":status = "Returning";break;
                        default:break;
                    }

                    Assignment assignment = new Assignment(
                            assignmentId,
                            assetID,
                            assetName,
                            categoryName,
                            user,
                            admin,
                            startDate,
                            EndDate,
                            null,
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

    public List<Assignment> GetAllAssignmentByUser(String IdUser){
        List<Assignment> listAssignment = new ArrayList<>();
        try{
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if(connect != null){
                String query = "SELECT assign.[Assignment_id], a.[Asset_id], a.[Asset_Name], assign.[EndDate], c.[Category_Name], " +
                        "assign.[Status], assign.[StartDate] " +
                        "FROM Assignment assign " +
                        "INNER JOIN [Asset] a ON assign.[Asset_id] = a.[Asset_id] " +
                        "INNER JOIN [Category] c ON assign.[Category_id] = c.[Category_id] "+
                        "where assign.[Status] > 0 AND assign.[User_id] = ?";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, IdUser);
                ResultSet rs = preparedStatement.executeQuery();

                while(rs.next()){
                    String assetID = rs.getString("Asset_id");
                    String assetName = rs.getString("Asset_Name");
                    String categoryName = rs.getString("Category_Name");
                    String startDate = rs.getString("StartDate");
                    String EndDate = rs.getString("EndDate");
                    String status = rs.getString("Status");
                    Log.e("Asset_id",assetID);
                    Log.e("assetName",assetName);
                    Log.e("startDate",startDate);
                    Log.e("categoryName",categoryName);
                    Log.e("status",status);
                    switch (status){
                        case "1":status = "Assigned";break;
                        case "2":status = "Returned";break;
                        case "3":status = "Returning";break;
                        default:break;
                    }

                    Assignment assignment = new Assignment(
                            null,
                            assetID,
                            assetName,
                            categoryName,
                            null,
                            null,
                            startDate,
                            null,
                            null,
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

    public List<Assignment> GetAllAssignment_NotHandler(){
        List<Assignment> listAssignment = new ArrayList<>();
        try{
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if(connect != null){
                String query = "SELECT assign.[Assignment_id], assign.[EndDate],assign.[Description], c.[Category_Name], " +
                        "u_user.Fullname AS UserFullName, u_admin.Fullname AS AdminFullName, assign.[Status], assign.[StartDate] " +
                        "FROM Assignment assign " +
                        "LEFT JOIN [User] u_admin ON assign.[Admin_id] = u_admin.[User_id] AND u_admin.[Role_id] = 1 " +
                        "LEFT JOIN [User] u_user ON assign.[User_id] = u_user.[User_id] AND u_user.[Role_id] = 2 " +
                        "INNER JOIN [Category] c ON assign.[Category_id] = c.[Category_id] "+
                        "where assign.[Status] = 0";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                while(rs.next()){
                    String assignmentId = rs.getString("Assignment_id");
                    String categoryName = rs.getString("Category_Name");
                    String user = rs.getString("UserFullName");
                    String admin = rs.getString("AdminFullName");
                    String startDate = rs.getString("StartDate");
                    String status = rs.getString("Status");
                    String description = rs.getString("Description");

                    switch (status){
                        case "0":status = "Pending";break;
                        default:break;
                    }

                    Assignment assignment = new Assignment(
                            assignmentId,
                            null,
                            null,
                            categoryName,
                            user,
                            admin,
                            startDate,
                            null,
                            description,
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
    public void AddAssignment(){
        try {
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if (connect != null) {
                String query = "INSERT INTO [dbo].[Assignment] ([Asset_id],[Category_id],[User_id],[Admin_id],[StartDate],[EndDate],[Description],[Status]) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, asset_code);
                preparedStatement.setString(2, category);
                preparedStatement.setString(3, assigned_to);
                preparedStatement.setString(4, assigned_by);
                preparedStatement.setString(5, assigned_date);
                preparedStatement.setString(6, endDate);
                preparedStatement.setString(7, description);
                preparedStatement.setString(8, status);
                int rowsDeleted = preparedStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    // Delete was successful
                }
                else{
                    //Delete was insuccessfull
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void UpdateRequestAssignment(String AdminID,String assignmentID,String assetID,
                                 String category_id,
                                 String currentDate,String returnDate,
                                 String state){
        try {
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if (connect != null) {
                String queryString = "UPDATE [dbo].[Assignment] SET Status = ?, Admin_id = ?, " +
                        "Asset_id = ?, Category_id = ?, StartDate = ?, EndDate = ? " +
                        "WHERE Assignment_id = ?";
                PreparedStatement preparedStatement = connect.prepareStatement(queryString);
                preparedStatement.setString(1, state);
                preparedStatement.setString(2, AdminID);
                preparedStatement.setString(3, assetID);
                preparedStatement.setString(4, category_id);
                preparedStatement.setString(5, currentDate);
                preparedStatement.setString(6, returnDate);
                preparedStatement.setString(7, assignmentID);

                int rowsDeleted = preparedStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    // Delete was successful
                }
                else{
                    //Delete was insuccessfull
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void UpdateEditAssignment(String assignmentID,
                                        String returnDate,
                                        String state){
        try {
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if (connect != null) {
                String queryString = "UPDATE [dbo].[Assignment] SET Status = ?, " +
                        "EndDate = ? " +
                        "WHERE Assignment_id = ?";
                PreparedStatement preparedStatement = connect.prepareStatement(queryString);
                preparedStatement.setString(1, state);
                preparedStatement.setString(2, returnDate);
                preparedStatement.setString(3, assignmentID);

                int rowsDeleted = preparedStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    // Delete was successful
                }
                else{
                    //Delete was insuccessfull
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void DeleteAssigment(String AssignmentID){
        try {
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if (connect != null) {
                String query = "DELETE FROM [dbo].[Assignment] " +
                        "WHERE Assignment_id = ?";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, AssignmentID);

                int rowsDeleted = preparedStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    // Delete was successful
                }
                else{
                    //Delete was insuccessfull
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
