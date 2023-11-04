package com.example.navigationdrawer.model;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.navigationdrawer.model.SQLServer.SQLServer;

public class Role {
    String id, name;

    private Connection connect;
    public Role(){

    }

    public Role(String id, String name) {
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
        public List<Role> GetAllRole(){
        List<Role> listRole = new ArrayList<>();
        try{
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if(connect != null){
                String query = "SELECT u.* " +
                        "FROM [dbo].[Role] u ";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    String idRole = rs.getString("Role_id");
                    String nameRoler = rs.getString("Role_name");
                    Role u = new Role(idRole,
                            nameRoler);
                    listRole.add(u);

                }
                return listRole;

            }
        }catch(Exception e){
            Log.e(e.getMessage(),"Log error");
        }
        return null;
    }
}
