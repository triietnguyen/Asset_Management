package Models;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Models.SQLServer.SQLServer;

public class Asset {
    private Connection connect;
    public Asset(){

    }

    public List<String> GetAssetByCategoryName(String categoryName){
        List<String> listAsset = new ArrayList<>();
        try{
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if(connect != null){
                String query = "SELECT a.Asset_Name " +
                        "FROM [dbo].[Asset] a " +
                        "INNER JOIN [dbo].[Category] c ON a.Category_id = c.Category_id " +
                        "WHERE c.Category_Name = ?";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, categoryName);
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()){
                    String assetName = rs.getString("Asset_Name");
                    Log.e("test",assetName);
                    listAsset.add(assetName);

                }
                return listAsset;
            }
        }catch(Exception e){
            Log.e(e.getMessage(),"Log error");
        }
        return null;
    }
}
