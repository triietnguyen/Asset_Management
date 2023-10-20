package Models;

public class Asset {


    private String asset_id, asset_name, asset_category;

    private String status;

    public Asset(String asset_id, String asset_name, String asset_category, String status) {
        this.asset_id = asset_id;
        this.asset_name = asset_name;
        this.asset_category = asset_category;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(String asset_id) {
        this.asset_id = asset_id;
    }

    public String getAsset_name() {
        return asset_name;
    }

    public void setAsset_name(String asset_name) {
        this.asset_name = asset_name;
    }

    public String getAsset_category() {
        return asset_category;
    }

    public void setAsset_category(String asset_category) {
        this.asset_category = asset_category;
    }
}
