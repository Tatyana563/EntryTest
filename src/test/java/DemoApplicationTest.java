import com.domain.Driver;
import com.domain.Track;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.DriverService;
import com.service.impl.DriverServiceImpl;
import org.junit.Test;

public class DemoApplicationTest {
    DriverService<Driver> SERVICE = new DriverServiceImpl();

    @Test
    public void testDriverAndTruck() throws JsonProcessingException {

//        Driver driver = new Driver("Gleb", 40, "well-qualified");
//        Track track = new Track(2, 2015, "Fiat");
//        SERVICE.save(driver);


        // driverDao.save(driver);
    }

    @Test
    public void testDriver() {
        //  System.out.println( SERVICE.findAllByExperience("well-qualified"));
        SERVICE.deleteById(6);
    }

}
