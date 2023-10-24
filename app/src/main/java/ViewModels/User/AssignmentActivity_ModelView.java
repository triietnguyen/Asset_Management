package ViewModels.User;

import java.util.List;

import Models.Assignment;

public class AssignmentActivity_ModelView {
    public List<Assignment> GetAllAssignment(String userID){
        Assignment assignment = new Assignment();
        List<Assignment> listAssign = assignment.GetAllAssignmentByUser(userID);
        return listAssign;

    }
}
