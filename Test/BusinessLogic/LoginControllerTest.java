package BusinessLogic;
import DomainModel.User;
import ORM.UserDAO;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    @Test
    void adminLoginTest() {
        LoginController loginController = new LoginController();
        assertTrue(loginController.adminLogin("ADMIN"));
        assertFalse(loginController.adminLogin("admin"));
    }

    @Test
    void registerLoginTest() throws SQLException, ClassNotFoundException {
        LoginController loginController = new LoginController();
        loginController.register("testname","testsurname",0, "testcode",
                "testusername","testpassword");

        User user = loginController.login("testusername","testpassword");

        assertEquals(user.getName(),"testname");
        assertEquals(user.getSurname(),"testsurname");
        assertEquals(user.getLicenceCode(),"testcode");
        assertEquals(user.getAge(),0);

        UserDAO userDAO = new UserDAO();
        userDAO.removeUser("testusername");
    }
}