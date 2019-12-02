import com.domain.Driver;
import com.service.DriverService;
import com.service.impl.DriverServiceImpl;
import com.service.impl.TrackService;
import org.junit.Test;

public class DemoApplicationTest {
    DriverService<Driver> SERVICE = new DriverServiceImpl();
    TrackService truckService = new TrackService();

    @Test
    public void testTruck() {
        truckService.findAllByYear(2015);


        // driverDao.save(driver);
    }

    @Test
    public void testDriver() {
        //  System.out.println( SERVICE.findAllByExperience("well-qualified"));
        SERVICE.deleteById(6);
    }

}
