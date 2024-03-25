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
        UserDAO userDAO = new UserDAO();
        int intialUserSize = userDAO.selectAllUsers().size();

        LoginController loginController = new LoginController();
        loginController.register("testname","testsurname",0, "testcode",
                "testusername","testpassword");

        int finalUserSize = userDAO.selectAllUsers().size();
        assertEquals(finalUserSize, intialUserSize + 1);

        loginController.register("testname","testsurname",0, "testcode",
                "testusername","testpassword");

        int sameUsernameSize = userDAO.selectAllUsers().size();
        assertEquals(sameUsernameSize, finalUserSize);

        User user = loginController.login("testusername","testpassword");

        assertEquals(user.getName(),"testname");
        assertEquals(user.getSurname(),"testsurname");
        assertEquals(user.getLicenceCode(),"testcode");
        assertEquals(user.getAge(),0);

        userDAO.removeUser("testusername");
    }
}