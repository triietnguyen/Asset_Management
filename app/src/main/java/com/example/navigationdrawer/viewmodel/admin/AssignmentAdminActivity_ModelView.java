package com.example.navigationdrawer.viewmodel.admin;

import java.sql.Connection;
import java.util.List;

import com.example.navigationdrawer.model.Assignment;

public class AssignmentAdminActivity_ModelView {

    private Connection connect;
    public List<Assignment> GetAllAssignment(){
        Assignment assignment = new Assignment();
        List<Assignment> listAssign = assignment.GetAllAssignmentAvailable();
        return listAssign;

    }


}
