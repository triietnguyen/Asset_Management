package Models;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Models.SQLServer.SQLServer;

public class Account {
    private Connection connect;
    private String email;
    private String password;
    public int roleId;

    public Account(String email, String password){
        this.email = email;
        this.password = password;
    }
    public Account(String email){
        this.email = email;

    }

    public String GetEmail(){
        return this.email;
    }
    public String SetEmail(String email) {
        return this.email = email;
    }

    public String GetPassword(){
        return this.password;
    }
    public String SetPassword(String password) {
        return this.password = password;
    }

    public int GetRoleId(){
        return this.roleId;
    }
    public int SetRoleId(int roleId) {
        return this.roleId = roleId;
    }


    public boolean IsValidEmail(){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public boolean IsValidPassword(){
        return !TextUtils.isEmpty(password) && password.length() >=6;
    }
    public boolean IsCheckValidAccount(){
        try{
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if(connect != null){

                String query = "SELECT u.* " +
                        "FROM [dbo].[User] u";

                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);

                while(rs.next()){
                    Log.e("4444", "4444");
                    String userDB = rs.getString("Email");
                    String passwordDB = rs.getString("Password");
                    String role_Id = rs.getString("Role_Id");
                    if((email.equalsIgnoreCase(userDB)) && (passwordDB.equalsIgnoreCase(password))){
                        Log.e(""+role_Id,"DB");
                        SetRoleId(Integer.parseInt(role_Id));
                        return true;
                    }

                }
                return false;
            }

        }catch(Exception e){
            Log.e(e.getMessage(),"Log error");
        }
        return false;
    }

    public boolean IsCheckValidPassword(){
        try{
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if(connect != null){
                String query = "SELECT u.* " +
                        "FROM [dbo].[User] u "+
                        "WHERE Email = ?";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, email);
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()){
                    String passwordDB = rs.getString("Password");
                    if((password.equalsIgnoreCase(passwordDB)) ){
                        return true;
                    }
                }
                return false;
            }
        }catch(Exception e){
            Log.e(e.getMessage(),"Log error");
        }
        return false;
    }

    public void UpdateImg(String imgURL){
        try{
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if(connect != null){
                String query = "UPDATE [dbo].[User] SET Image = ? WHERE Email = ?";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, imgURL);
                preparedStatement.setString(2, email);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    Log.e("Image","Cap nhat thanh cong");
                } else {
                    Log.e("Image","Cap nhat khong thanh cong");
                }
            }
        }catch(Exception e){
            Log.e(e.getMessage(),"Log error");
        }
    }

    public boolean IsUpdatePassword(){
        try{
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if(connect != null){
                String query = "UPDATE [dbo].[User] SET Password = ? WHERE Email = ?";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, password);
                preparedStatement.setString(2, email);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    return true;

                } else {
                    return false;
                }
            }

        }catch(Exception e){
            Log.e(e.getMessage(),"Log error");
        }
        return false;
    }

    public Map<String,String> GetUserFromDB(){

        Map<String, String> userDB = new HashMap<>();
        try{
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if(connect != null){
                String query = "SELECT u.* " +
                        "FROM [dbo].[User] u "+
                        "WHERE u.Email = ?";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, email);

                ResultSet rs = preparedStatement.executeQuery();
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                while(rs.next()){
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        String columnValue = rs.getString(i);

                        userDB.put(columnName,columnValue);

                        Log.e("Column Name: " + columnName, "Column Value: " + columnValue);
                    }
                }

            }

        }catch(Exception e){
            Log.e(e.getMessage(),"Log error");
        }
        return userDB;
    }


}
