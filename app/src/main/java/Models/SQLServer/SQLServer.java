package Models.SQLServer;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLServer {
    Connection con;
    String uname, pass, ip, port, database;
    @SuppressLint("NewApi")
    public Connection ConnectionSql(){
        ip = "10.102.73.108";
        database = "Asset_Management";
        uname = "sa";
        pass = "1";
        port = "1433";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String connectionURL = null;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL ="jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databasename=" + database +";user="+ uname +";password=" + pass +";";
            connection = DriverManager.getConnection(connectionURL);
        }catch(Exception e){
            Log.e("error",e.getMessage());
        }

        return connection;
    }
}
