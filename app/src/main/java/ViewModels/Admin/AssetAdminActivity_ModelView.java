package ViewModels.Admin;

import java.sql.Connection;
import java.util.List;

import Models.Asset;
import Models.User;

public class AssetAdminActivity_ModelView {

    private Connection connect;
    public List<Asset> GetAllAsset(){
        Asset asset = new Asset();
        List<Asset> listUser = asset.GetAllAsset();
        return listUser;

    }


}
