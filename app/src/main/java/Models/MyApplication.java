package Models;

import android.app.Application;

public class MyApplication extends Application {
    private String sharedData;

    public String GetSharedData() {
        return sharedData;
    }

    public void SetSharedData(String data) {
        this.sharedData = data;
    }
}
