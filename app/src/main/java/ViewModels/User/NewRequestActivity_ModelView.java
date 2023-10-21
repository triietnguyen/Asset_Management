package ViewModels.User;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.navigationdrawer.BR;

import java.util.List;

import Models.Asset;
import Models.Category;

public class NewRequestActivity_ModelView extends BaseObservable {
    String date,description;

    @Bindable
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
        notifyPropertyChanged(BR.date);
    }

    @Bindable
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    public List<Category> GetAllCategory(){
        Category c = new Category();
        return c.GetAllCategory();
    }
    public List<Asset> GetAllAssetByCategory(String categoryName){
        Asset a = new Asset();
        return a.GetAssetByCategoryName(categoryName);
    }

}
