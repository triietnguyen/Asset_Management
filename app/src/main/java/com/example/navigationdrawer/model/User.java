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

public class User {
    String id, email, password, name, address, date_of_birth, gender, joined_date, image, role_id, phone;

    private Connection connect;
    public User(){

    }
    public User(String id, String email, String password, String name, String address, String date_of_birth, String gender, String joined_date, String image, String role_id, String phone) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.joined_date = joined_date;
        this.image = image;
        this.role_id = role_id;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJoined_date() {
        return joined_date;
    }

    public void setJoined_date(String joined_date) {
        this.joined_date = joined_date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String GetNameUserByEmail(String email) {
        try {
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if (connect != null) {
                String query = "SELECT u.User_id " +
                        "FROM [dbo].[User] u " +
                        "WHERE u.Email = ?";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, email);

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    String idUser = rs.getString("User_id");
                    return idUser;
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

        public List<User> GetAllUser(){
        List<User> listUser = new ArrayList<>();
        try{
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if(connect != null){

                String query ="Select *\n" +
                        "From [dbo].[User]";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    String idUser = rs.getString("User_id");
                    String emailUser = rs.getString("Email");
                    String passwordUser = rs.getString("Password");
                    String fullNameUser = rs.getString("Fullname");
                    String addressUser = rs.getString("Address");
                    String dateUser = rs.getString("Date_of_birth");
                    String genderUser = rs.getString("Gender");
                    String dateJoinUser = rs.getString("Joined_Date");
                    String imageUser = rs.getString("Image");
                    String roleIdUser = rs.getString("Role_id");
                    String phoneUser = rs.getString("Phone");

                    if(roleIdUser.equalsIgnoreCase("1"))
                        roleIdUser = "Admin";
                    else
                        roleIdUser = "User";

                    User u = new User(idUser,
                            emailUser,
                            passwordUser,
                            fullNameUser,
                            addressUser,
                            dateUser,
                            genderUser,
                            dateJoinUser,
                            imageUser,
                            roleIdUser,
                            phoneUser);
                    listUser.add(u);

                }
                return listUser;

            }
        }catch(Exception e){
            Log.e(e.getMessage(),"Log error");
        }
        return null;
    }

    public void AddUser(){
        try {
            SQLServer connection = new SQLServer();
            connect = connection.ConnectionSql();
            if (connect != null) {
                String query = "INSERT INTO [dbo].[User] ([Email],[Password],[Fullname],[Address],[Date_of_birth],[Gender],[Joined_Date],[Image],[Role_id],[Phone])" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, name);
                preparedStatement.setString(4, address);
                preparedStatement.setString(5, date_of_birth);
                preparedStatement.setString(6, gender);
                preparedStatement.setString(7, joined_date);
                preparedStatement.setString(8, image);
                preparedStatement.setString(9, role_id);
                preparedStatement.setString(10,phone);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
