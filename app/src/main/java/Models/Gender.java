package Models;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.SQLServer.SQLServer;

public class Gender {
    String id, name;

    private Connection connect;
    public Gender(){

    }

    public Gender(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
        public List<Gender> GetAllGender(){
        List<Gender> listGender = new ArrayList<>();
        try{
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if(connect != null){
                String query = "SELECT u.* " +
                        "FROM [dbo].[Gender] u ";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    String idGender = rs.getString("Gender_id");
                    String namelGender = rs.getString("Gender_name");
                    Gender u = new Gender(idGender,
                            namelGender);
                    listGender.add(u);

                }
                return listGender;

            }
        }catch(Exception e){
            Log.e(e.getMessage(),"Log error");
        }
        return null;
    }
}
