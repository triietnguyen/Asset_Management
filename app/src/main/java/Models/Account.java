package Models;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import SQLServer.SQLServer;

public class Account {
    private Connection connect;
    public int roleId;

    public int GetRoleId(){
        return this.roleId;
    }
    public int SetRoleId(int roleId) {
        return this.roleId = roleId;
    }
    public Account(){};

    public boolean IsCheckValidAccount(String userName, String password){
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
                    String userDB = rs.getString("Username");
                    String passwordDB = rs.getString("Password");
                    String role_Id = rs.getString("Role_Id");
                    Log.e("11" , userName);
                    Log.e("22" , password);
                    Log.e("7777", role_Id);
                    if((userName.equalsIgnoreCase(userDB)) && (passwordDB.equalsIgnoreCase(password))){
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
}
