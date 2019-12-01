import com.domain.Driver;
import com.domain.Track;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.CRUDService;
import com.service.DriverService;
import com.service.impl.DriverServiceImpl;
import com.service.impl.TrackService;
import org.junit.Test;

public class DemoApplicationTest {
    DriverService<Driver> SERVICE = new DriverServiceImpl();
    CRUDService<Track> TRUCKRESVICE = new TrackService();

    @Test
    public void testTruck() {
TRUCKRESVICE.findAllByYear(2015);



        // driverDao.save(driver);
    }

    @Test
    public void testDriver() {
        //  System.out.println( SERVICE.findAllByExperience("well-qualified"));
        SERVICE.deleteById(6);
    }

}
