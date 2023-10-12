package Models;

import android.app.Application;

public class MyApplication extends Application {
    private static MyApplication instance;
    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    private String sharedData;

    public String GetSharedData() {
        return sharedData;
    }

    public void SetSharedData(String data) {
        this.sharedData = data;
    }
}
