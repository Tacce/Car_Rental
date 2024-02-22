package BusinessLogic;

import ORM.CarDAO;
import ORM.MopedDAO;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AdminControllerTest {

    @Test
    void insertRemoveCarTest() throws SQLException, ClassNotFoundException {
        CarDAO carDAO = new CarDAO();
        int initialSize = carDAO.selectAllCars().size();

        AdminController adminController = new AdminController();
        String simulatedInput = "testplate\ntestmodel\n0\n0\n0\n";
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        adminController.addCar();
        System.setIn(originalSystemIn);

        int afterInsertSize = carDAO.selectAllCars().size();
        assertEquals(afterInsertSize, initialSize + 1);

        simulatedInput = String.format("%d\n", afterInsertSize);
        originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        adminController.removeCar();
        System.setIn(originalSystemIn);

        int afterDeleteSize = carDAO.selectAllCars().size();
        assertEquals(afterDeleteSize, initialSize);
    }

    @Test
    void insertRemoveMopedTest() throws SQLException, ClassNotFoundException {
        MopedDAO mopedDAO = new MopedDAO();
        int initialSize = mopedDAO.selectAllMopeds().size();

        AdminController adminController = new AdminController();
        String simulatedInput = "testplate\ntestmodel\n0\n0\n";
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        adminController.addMoped();
        System.setIn(originalSystemIn);

        int afterInsertSize = mopedDAO.selectAllMopeds().size();
        assertEquals(afterInsertSize, initialSize + 1);

        simulatedInput = String.format("%d\n", afterInsertSize);
        originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        adminController.removeMoped();
        System.setIn(originalSystemIn);

        int afterDeleteSize = mopedDAO.selectAllMopeds().size();
        assertEquals(afterDeleteSize, initialSize);
    }

}