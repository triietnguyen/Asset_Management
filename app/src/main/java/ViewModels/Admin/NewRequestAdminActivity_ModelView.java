package ViewModels.Admin;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.navigationdrawer.BR;

import java.util.List;

import Models.Asset;
import Models.Category;

public class NewRequestAdminActivity_ModelView extends BaseObservable {
    String date;
    @Bindable
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
        notifyPropertyChanged(BR.date);
    }

    public List<String> GetAllCategory(){
       Category c = new Category();
       return c.GetAllCategory();
    }
    public List<String> GetAllAssetByCategory(String categoryName){
        Asset a = new Asset();
        return a.GetAssetByCategoryName(categoryName);
    }
//
//    public void OnClickSaveButton(){
//
//    }
}
