package my.app.service;
 
import java.util.List;
 
import my.app.dao.SupervisorDAO;
import my.app.model.Supervisor;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
/**
 * @author rmontag
 *
 */
@Service
public class SupervisorService {
 
    @Autowired private SupervisorDAO supervisorDao;
 
    @Transactional(readOnly=true)
    public List getSupervisors() {
        return supervisorDao.getSupervisors();
    }
 
    /**
     * Determine if the supervisor is responsible for any users for the
     * specified department
     * @param dbId The dbId of the supervisor
     * @param department The department to look for
     * @return true if there are users assigned to the superivsor for the department
     */
    public boolean isSupervisorResponsibleForUser(int dbId, String department) {
        if (dbId == 0) {
            throw new IllegalArgumentException("A dbId is required");
        }
        if (department == null) {
            throw new IllegalArgumentException("department is required");
        }
        boolean hasUsers = false;
        int numAssignedUsers = supervisorDao.getUserCountForSupervisor(dbId, department);
        if (numAssignedUsers > 0) {
            hasUsers = true;
        }
        return hasUsers;
    }
}