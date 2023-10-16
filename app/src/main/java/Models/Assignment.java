package Models;

public class Assignment {

    String id, category;

    String asset_code, asset_name,assigned_to,assigned_by, assigned_date;

    String status;

    public Assignment(String id, String asset_code, String asset_name, String assigned_to, String assigned_by, String assigned_date, String status) {
        this.id = id;
        this.asset_code = asset_code;
        this.asset_name = asset_name;
        this.assigned_to = assigned_to;
        this.assigned_by = assigned_by;
        this.assigned_date = assigned_date;
        this.status = status;
    }

    public Assignment(String asset_code, String asset_name, String category, String assigned_date, String status) {
        this.asset_code = asset_code;
        this.asset_name = asset_name;
        this.category = category;
        this.assigned_date = assigned_date;
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAsset_code() {
        return asset_code;
    }

    public void setAsset_code(String asset_code) {
        this.asset_code = asset_code;
    }

    public String getAsset_name() {
        return asset_name;
    }

    public void setAsset_name(String asset_name) {
        this.asset_name = asset_name;
    }

    public String getAssigned_to() {
        return assigned_to;
    }

    public void setAssigned_to(String assigned_to) {
        this.assigned_to = assigned_to;
    }

    public String getAssigned_by() {
        return assigned_by;
    }

    public void setAssigned_by(String assigned_by) {
        this.assigned_by = assigned_by;
    }

    public String getAssigned_date() {
        return assigned_date;
    }

    public void setAssigned_date(String assigned_date) {
        this.assigned_date = assigned_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
