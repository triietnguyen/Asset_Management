package ViewModels.Admin;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.navigationdrawer.BR;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Models.Assignment;
import Models.Category;
import Models.Gender;
import Models.MyApplication;
import Models.Role;
import Models.User;

public class UserAdminActivity_ModelView extends BaseObservable {
    String date,email,password,fullname,address,datebirth,phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
        notifyPropertyChanged(BR.fullname);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.address);
    }

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

    public void OnClickSaveButton(Context context) {
        if(date == null){
            return;
        }
        Calendar calendar = Calendar.getInstance();

        int dateCalendar = calendar.get(Calendar.DATE);
        int monthCalendar = calendar.get(Calendar.MONTH);
        int yearCalendar = calendar.get(Calendar.YEAR);
        calendar.set(yearCalendar,monthCalendar,dateCalendar);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate_Str = simpleDateFormat.format(calendar.getTime());


        Date currentDate = null;
        try {
            currentDate = simpleDateFormat.parse(currentDate_Str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date dateOfUser = null;
        try {
            dateOfUser = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        if(dateOfUser.compareTo(currentDate) > 0){
            SharedPreferences sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE);
            email = sharedPreferences.getString("Email","");
            password = sharedPreferences.getString("Password","");
            fullname = sharedPreferences.getString("Fullname","");
            address = sharedPreferences.getString("Address","");
            datebirth = sharedPreferences.getString("Date_of_birth","");
            String gender = sharedPreferences.getString("Gender","");
            String image = "content://com.android.providers.media.documents/document/image%3A37";
            String role = sharedPreferences.getString("Role_id","");
            phone = sharedPreferences.getString("Phone","");

            User a = new User(null,email,password,fullname,address,datebirth,gender,date,image,role,phone);
            a.AddUser();

            ((Activity) context).finish();
        }
        else if(dateOfUser.compareTo(currentDate) < 0){
            Toast.makeText(context, "Ngay Thang Nam Phai lon hon ngay hien tai ", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            return;
        }

    }

}
