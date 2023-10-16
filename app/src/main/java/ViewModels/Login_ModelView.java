package ViewModels;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.navigationdrawer.BR;
import com.example.navigationdrawer.main.MainActivity;
import com.example.navigationdrawer.main.MainAdminActivity;

import Models.Account;
import Models.MyApplication;

public class Login_ModelView extends BaseObservable {
    private String email;
    private String password;
    @Bindable
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email); // BR.email được sinh ra tự động bởi Data Binding
    }

    @Bindable
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password); // BR.email được sinh ra tự động bởi Data Binding
    }
    public void OnClickButton(Context context){
        int roleAdmin = 1;
        int roleUser = 2;
        Account account = new Account(email,password);
        if(account.IsValidEmail() && account.IsValidPassword()){
//            Log.e("Login",account.GetEmail() + account.GetPassword());
            if(account.IsCheckValidAccount() && account.GetRoleId() == roleUser){
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                Toast.makeText(context, "Login User Page", Toast.LENGTH_SHORT).show();
                MyApplication.getInstance().SetSharedData(email.trim());
            }
            else if(account.IsCheckValidAccount() && account.GetRoleId() == roleAdmin){
                Intent intent = new Intent(context, MainAdminActivity.class);
                context.startActivity(intent);
                Toast.makeText(context, "Login Admin Page", Toast.LENGTH_SHORT).show();
                MyApplication.getInstance().SetSharedData(email.trim());
            }
            else Toast.makeText(context, "Đăng nhập sai", Toast.LENGTH_SHORT).show();
        }
        else{
            Log.e("False",account.GetEmail() + account.GetPassword());
        }
    }
}
