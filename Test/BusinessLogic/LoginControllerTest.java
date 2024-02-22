package BusinessLogic;
import DomainModel.User;
import ORM.UserDAO;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    @Test
    void testAdminLogin() {
        LoginController loginController = new LoginController();
        String simulatedInput = "ADMIN\n";
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        assertTrue(loginController.adminLogin());

        System.setIn(originalSystemIn);
        simulatedInput = "admin\n";
        originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        assertFalse(loginController.adminLogin());
        System.setIn(originalSystemIn);
    }

    @Test
    void testRegisterLogin() throws SQLException, ClassNotFoundException {
        LoginController loginController = new LoginController();
        String simulatedInput = "testname\ntestsurname\n0\ntestcode\ntestusername\ntestpassword\n";
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        loginController.register();
        System.setIn(originalSystemIn);

        simulatedInput = "testusername\ntestpassword\n";
        originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        User user = loginController.login();
        System.setIn(originalSystemIn);

        assertEquals(user.getName(),"testname");
        assertEquals(user.getSurname(),"testsurname");
        assertEquals(user.getLicence_code(),"testcode");
        assertEquals(user.getAge(),0);

        UserDAO userDAO = new UserDAO();
        userDAO.removeUser("testusername");
    }
}