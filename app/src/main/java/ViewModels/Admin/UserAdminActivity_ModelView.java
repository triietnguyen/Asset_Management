package ViewModels.Admin;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.navigationdrawer.BR;

import java.sql.Connection;
import java.util.List;

import Models.Assignment;
import Models.Category;
import Models.Gender;
import Models.Role;
import Models.User;

public class UserAdminActivity_ModelView extends BaseObservable {
    String date;
    @Bindable
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
        notifyPropertyChanged(BR.date);
    }

    public List<User> GetAllUser(){
        User user = new User();
        List<User> listUser = user.GetAllUser();
        return listUser;

    }

    public List<Gender> GetAllGender(){
        Gender g = new Gender();
        return g.GetAllGender();
    }

    public List<Role> GetAllRole(){
        Role role = new Role();
        List<Role> listRole = role.GetAllRole();
        return listRole;

    }

}
