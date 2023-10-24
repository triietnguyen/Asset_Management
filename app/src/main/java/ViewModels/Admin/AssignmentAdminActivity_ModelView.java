package ViewModels.Admin;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.navigationdrawer.BR;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.Assignment;
import Models.SQLServer.SQLServer;

public class AssignmentAdminActivity_ModelView {

    private Connection connect;
    public List<Assignment> GetAllAssignment(){
        Assignment assignment = new Assignment();
        List<Assignment> listAssign = assignment.GetAllAssignmentAvailable();
        return listAssign;

    }


}
