package ViewModels.Admin;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.navigationdrawer.BR;

import java.util.HashMap;
import java.util.Map;

import Models.Account;
import Models.MyApplication;

public class MainAdmin_ModelView extends BaseObservable {
    private String fullName;
    private String email;
    private String image;
    Map<String, String> userMap = new HashMap<>();

    @Bindable
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
        notifyPropertyChanged(BR.fullName);
    }

    @Bindable
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        String data = MyApplication.getInstance().GetSharedData();
        Account account = new Account(data);
        account.UpdateImg(image);
    }
    public void GetData(){
        String emailDB,fullnameDb,imageDB;

        String data = MyApplication.getInstance().GetSharedData();
        Account account = new Account(data);
        userMap = account.GetUserFromDB();

        emailDB = userMap.get("Email");
        fullnameDb = userMap.get("Fullname");
        imageDB = userMap.get("Image");

        this.setEmail(emailDB);
        this.setFullName(fullnameDb);
        this.setImage(imageDB);
    }
}
