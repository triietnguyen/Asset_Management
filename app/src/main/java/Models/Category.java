package Models;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.SQLServer.SQLServer;

public class Category {
    private Connection connect;
    public Category(){

    }
    public List<String> GetAllCategory(){
        List<String> listCategory = new ArrayList<>();
        try{
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if(connect != null){
                String query = "SELECT c.Category_Name " +
                        "FROM [dbo].[Category] c";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    String categoryName = rs.getString("Category_Name");

                    listCategory.add(categoryName);

                }
                return listCategory;

            }
        }catch(Exception e){
            Log.e(e.getMessage(),"Log error");
        }
        return null;
    }
}
