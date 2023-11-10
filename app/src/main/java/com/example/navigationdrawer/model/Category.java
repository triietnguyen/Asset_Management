package com.example.navigationdrawer.model;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.navigationdrawer.model.SQLServer.SQLServer;

public class Category {
    private static Connection connect;
    String category_Id,category_Name;
    public Category(){

    }

    public Category(String category_Id, String category_Name){
        this.category_Id = category_Id;
        this.category_Name = category_Name;
    }


    public String GetID(){return category_Id;}
    public String GetName(){return category_Name;}

    public static String GetIDByCategory(String CategoryName){
        try{
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if(connect != null){
                String query = "SELECT c.* " +
                        "FROM [dbo].[Category] c "+
                        "Where Category_Name = ?";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, CategoryName);
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()){
                    String id = rs.getString("Category_id");
                    return id;

                }


            }
        }catch(Exception e){
            Log.e(e.getMessage(),"Log error");
        }
        return null;
    }
    public List<Category> GetAllCategory(){
        List<Category> listCategory = new ArrayList<>();
        try{
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if(connect != null){
                String query = "SELECT c.* " +
                        "FROM [dbo].[Category] c";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    String id = rs.getString("Category_id");
                    String name = rs.getString("Category_Name");
                    Category c = new Category(id,name);
                    listCategory.add(c);

                }
                return listCategory;

            }
        }catch(Exception e){
            Log.e(e.getMessage(),"Log error");
        }
        return null;
    }


}
