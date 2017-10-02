package my.app.service;
 
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
 
import my.app.dao.SupervisorDAO;
 
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
 
@RunWith(MockitoJUnitRunner.class)
public class SupervisorServiceUnitTest {
 
    @Mock private SupervisorDAO mockSupervisorDao;
    @InjectMocks private SupervisorService supervisorService;
 
    @Before
    public void init() {
        when(mockSupervisorDao.getUserCountForSupervisor(1, "Department1"))
            .thenReturn(2);
        when(mockSupervisorDao.getUserCountForSupervisor(1, "Department2"))
            .thenReturn(0);
    }
 
    @Test
    public void testIsSupervisorResponsibleForUserTrue() {
        boolean isSupervisor = supervisorService.isSupervisorResponsibleForUser(1, "Department1");
        assertTrue(isSupervisor);
    }
 
    @Test
    public void testIsSupervisorResponsibleForUserFalse() {
        boolean isSupervisor = supervisorService.isSupervisorResponsibleForUser(1, "Department2");
        assertFalse(isSupervisor);
    }
 
    @Test(expected=IllegalArgumentException.class)
    public void testIsSupervisorResponsibleRequiredDbId() {
        supervisorService.isSupervisorResponsibleForUser(0, "Department3");
    }
 
    @Test(expected=IllegalArgumentException.class)
    public void testIsSupervisorResponsibleRequiredDivision() {
        supervisorService.isSupervisorResponsibleForUser(1, null);
    }
}
