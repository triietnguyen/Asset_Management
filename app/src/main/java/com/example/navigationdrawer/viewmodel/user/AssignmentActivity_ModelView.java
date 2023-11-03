package com.example.navigationdrawer.viewmodel.user;

import java.util.List;

import com.example.navigationdrawer.model.Assignment;

public class AssignmentActivity_ModelView {
    public List<Assignment> GetAllAssignment(String userID){
        Assignment assignment = new Assignment();
        List<Assignment> listAssign = assignment.GetAllAssignmentByUser(userID);
        return listAssign;

    }
}
