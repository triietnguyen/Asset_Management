package ViewModels.Admin;

import java.sql.Connection;
import java.util.List;

import Models.Assignment;
import Models.User;

public class UserAdminActivity_ModelView {

    private Connection connect;
    public List<User> GetAllUser(){
        User user = new User();
        List<User> listUser = user.GetAllUser();
        return listUser;

    }


}
