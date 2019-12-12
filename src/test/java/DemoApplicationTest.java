import com.domain.Driver;
import com.domain.Track;
import com.service.DriverService;
import com.service.impl.DriverServiceImpl;
import com.service.impl.TrackService;
import org.junit.Test;

import static com.domain.Experience.LACK_OF_EXPERIENCE;
import static com.domain.Experience.WELL_QUALIFIED;

public class DemoApplicationTest {
    DriverService<Driver> SERVICE = new DriverServiceImpl();
    TrackService truckService = new TrackService();

    @Test
    public void testTruck() {
       // truckService.findAllByYear(2015);//+
      //  truckService.updateModelByModelYear(2013, "EDICOLA"); //+
        System.out.println(truckService.findOnlyTrucksByYear(2018));//-
        //  truckService.save(new Track(20,2018,"ROAD-KINGS",new Driver(6,"Frank",32,WELL_QUALIFIED)));//-
// add truck to the existing driver. driver with id 8 is already in the DB
Driver driver = new Driver(8);
 truckService.save(new Track(0,2018,"ROAD-KINGS", driver));//-

//truckService.update(track);//+
// truckService.deleteById(15);//+

    }

    @Test
    public void testDriver() {
       //   System.out.println( SERVICE.findAllByExperience(WELL_QUALIFIED));// - no grouping
     //   SERVICE.deleteById(9); //+
      //  SERVICE.updateExperienceByName("Frank", "LACK_OF_EXPERIENCE");//+
      //  System.out.println(SERVICE.findAllByNumberOfTrucks(3));//+
        //SERVICE.updateQualificationByWorkload(3,2017);//+
    }

}
