package com.example.navigationdrawer.model;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.navigationdrawer.model.SQLServer.SQLServer;

public class Asset {
    private Connection connect;
    private String asset_id, asset_name, asset_category, status;

    public Asset(String asset_id, String asset_name, String asset_category, String status) {
        this.asset_id = asset_id;
        this.asset_name = asset_name;
        this.asset_category = asset_category;
        this.status = status;
    }

    public Asset() {

    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status =  status;
    }

    public String getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(String asset_id) {
        this.asset_id = asset_id;
    }

    public String getAsset_name() {
        return asset_name;
    }

    public void setAsset_name(String asset_name) {
        this.asset_name = asset_name;
    }

    public String getAsset_category() {
        return asset_category;
    }

    public void setAsset_category(String asset_category) {
        this.asset_category = asset_category;
    }

    public List<Asset> GetAllAssets() {
        List<Asset> listAsset = new ArrayList<>();
        try {
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if (connect != null) {
                String query = "select [Asset_id],[Asset_Name],[Category_Name],[Status]" +
                        "from Asset a inner join [dbo].[Category] c on a.[Category_id] = c.Category_id ";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    String id = rs.getString("Asset_id");
                    String name = rs.getString("Asset_Name");
                    String categoryName = rs.getString("Category_Name");
                    String status = rs.getString("Status");

                    switch (status){
                        case "0":status = "Not Available";break;
                        case "1":status = "Available";break;
                        case "2":status = "Deleted";break;
                        default:break;
                    }

                    Asset a = new Asset(id, name, categoryName, status);
                    listAsset.add((a));

                }
                return listAsset;

            }
        } catch (Exception e) {
            Log.e(e.getMessage(), "Log error");
        }
        return null;
    }
    public List<Asset> GetAssetsAdminBySearch(String search, String filter, String status_filter){
        Log.e("search",search);
        List<Asset> listAsset = new ArrayList<>();
        try{
            String filterCondition = " ";
            if (!"All".equals(filter) && !"All".equals(status_filter)) {
                filterCondition = filter + " LIKE '" + search + "%' AND a.[Status] LIKE '" + status_filter + "'";
            }
            else{
                filterCondition =
                        "(a.[Asset_id] LIKE '" + search + "%' AND a.[Status] LIKE '" + status_filter + "' OR " +
                        "a.[Asset_Name] LIKE '" + search + "%' AND a.[Status] LIKE '" + status_filter + "' OR " +
                        "c.[Category_Name] LIKE '" + search + "%' AND a.[Status] LIKE '" + status_filter + "')";
            }
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if(connect != null){
                String query = "select [Asset_id],[Asset_Name],[Category_Name],[Status]" +
                        "from Asset a inner join [dbo].[Category] c on a.[Category_id] = c.Category_id " +
                        "WHERE " + filterCondition;
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);

                while(rs.next()){
                    String assetID = rs.getString("Asset_id");
                    String assetName = rs.getString("Asset_Name");
                    String categoryName = rs.getString("Category_Name");
                    String status = rs.getString("Status");

                    switch (status){
                        case "0":status = "Not Available";break;
                        case "1":status = "Available";break;
                        case "2":status = "Deleted";break;
                        default:break;
                    }

                    Asset a = new Asset(assetID, assetName, categoryName, status);
                    listAsset.add((a));
                }
                return listAsset;

            }
        }catch(Exception e){
            Log.e(e.getMessage(),"Log error");
        }
        return null;
    }

    public List<Asset> GetAssetByCategoryName(String categoryName) {
        List<Asset> listAsset = new ArrayList<>();
        try {
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if (connect != null) {
                String query = "SELECT a.* " +
                        "FROM [dbo].[Asset] a " +
                        "INNER JOIN [dbo].[Category] c ON a.Category_id = c.Category_id " +
                        "WHERE c.Category_Name = ? AND a.Status = 1";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, categoryName);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("Asset_id");
                    String name = rs.getString("Asset_Name");
                    String categoryId = rs.getString("Category_id");
                    String status = rs.getString("Status");
                    Asset a = new Asset(id,name,categoryId,status);
                    listAsset.add((a));

                }
                return listAsset;
            }
        } catch (Exception e) {
            Log.e(e.getMessage(), "Log error");
        }
        return null;
    }

    public void AddAsset(){
        try {
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if (connect != null) {
                String query = "INSERT INTO [dbo].[Asset] ([Asset_Name],[Category_id],[Status])" +
                        "VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, asset_name);
                preparedStatement.setString(2, asset_category);
                preparedStatement.setString(3, status);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void UpdateEditAsset(String asset_id, String asset_name, String status){
        try {
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if (connect != null) {
                String queryString = "UPDATE [dbo].[Asset] SET Status = ?, " +
                        "Asset_Name = ? " +
                        "WHERE Asset_id = ?";
                PreparedStatement preparedStatement = connect.prepareStatement(queryString);
                preparedStatement.setString(1, status);
                preparedStatement.setString(2, asset_name);
                preparedStatement.setString(3, asset_id);

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

    public void UpdateAssetStatus(String asset_id, String status){
        try {
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if (connect != null) {
                String queryString = "UPDATE [dbo].[Asset] SET Status = ? " +
                        "WHERE Asset_id = ?";
                PreparedStatement preparedStatement = connect.prepareStatement(queryString);
                preparedStatement.setString(1, status);
                preparedStatement.setString(2, asset_id);

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

    public void HandlerAsset(String AssetID){
        try {
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if (connect != null) {
                String queryString = "UPDATE [dbo].[Asset] SET Status = '2'" +
                        "WHERE Asset_id = ?";
                PreparedStatement preparedStatement = connect.prepareStatement(queryString);
                preparedStatement.setString(1, AssetID);
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

