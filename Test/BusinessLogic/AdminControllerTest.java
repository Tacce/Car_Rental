package BusinessLogic;

import DomainModel.Car;
import DomainModel.Moped;
import ORM.CarDAO;
import ORM.MopedDAO;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

class AdminControllerTest {

    @Test
    void insertRemoveCarTest() throws SQLException, ClassNotFoundException {
        AdminController adminController = new AdminController();
        int initialSize = adminController.viewAllCars().size();

        adminController.addCar("testplate","testmodel", 0,0,0);

        int afterInsertSize = adminController.viewAllCars().size();
        assertEquals(afterInsertSize, initialSize + 1);

        CarDAO carDAO = new CarDAO();

        Car car = carDAO.getCar("testplate");
        assertEquals(car.getModel(), "testmodel");
        assertEquals(car.getDailyPrice(), 0);
        assertEquals(car.getPlate(), "testplate");

        adminController.removeCar("testplate");

        int afterDeleteSize = adminController.viewAllCars().size();
        assertEquals(afterDeleteSize, initialSize);
    }

    @Test
    void insertRemoveMopedTest() throws SQLException, ClassNotFoundException {
        AdminController adminController = new AdminController();
        int initialSize = adminController.viewAllMopeds().size();

        adminController.addMoped("testplate","testmodel", 0,0);

        int afterInsertSize = adminController.viewAllMopeds().size();
        assertEquals(afterInsertSize, initialSize + 1);

        MopedDAO mopedDAO = new MopedDAO();

        Moped moped = mopedDAO.getMoped("testplate");
        assertEquals(moped.getModel(), "testmodel");
        assertEquals(moped.getDailyPrice(), 0);
        assertEquals(moped.getPlate(), "testplate");

        adminController.removeMoped("testplate");

        int afterDeleteSize = adminController.viewAllMopeds().size();
        assertEquals(afterDeleteSize, initialSize);
    }
}