package com.repository.postgre;

import com.domain.Driver;
import com.domain.Experience;
import com.domain.Track;
import com.dto.DriverDTO;
import com.repository.ConnectionFactory;
import netscape.security.UserTarget;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PosgreDriverDAO implements DriverDAO {

    @Override
    public void save(Driver driver) {
        Connection connection = ConnectionFactory.getConnection();
        String query = "INSERT INTO driver (driver_name, driver_age, qualification) VALUES (?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, driver.getName());
            statement.setInt(2, driver.getAge());
            statement.setString(3, driver.getExperience().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int number, int modelYear) {
String insertQuery =
        "UPDATE  driver " +
                "SET qualification= 'WELL_QUALIFIED' " +
                "WHERE ?<=( " +
                "SELECT COUNT(driver_fk_id) FROM track " +
                "WHERE driver.driver_id = track.driver_fk_id " +
                "AND model_year>?) ";
try(PreparedStatement statement =
        ConnectionFactory.getConnection().prepareStatement(insertQuery)){
    statement.setInt(1, number);
    statement.setInt(2, modelYear);
statement.executeUpdate();
} catch (SQLException e) {
    e.printStackTrace();
}


    }

    @Override
    public void deleteById(int driverId) {
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(
                "DELETE from driver as d WHERE d.driver_id=?"//uppercase
        )) {
            statement.setInt(1, driverId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DriverDTO> findAllOnlyDriversByExperience(Experience experience) {
        List<DriverDTO> driverList = new ArrayList<>();
        try (PreparedStatement statement =
                     ConnectionFactory.getConnection().prepareStatement(
                "SELECT * FROM Driver AS d WHERE d.qualification = ?" )) {
            statement.setString(1, experience.name());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int driverId = resultSet.getInt("driver_id");
                int driverAge = resultSet.getInt("driver_age");
                String driverName = resultSet.getString("driver_name");
                String driverQualification =
                        resultSet.getString("qualification");

                final Experience experience1 = Experience.valueOf(driverQualification);

                final Driver driver = new Driver(driverId, driverName, driverAge, experience1);
                final DriverDTO driverDto = new DriverDTO(driver);

                driverList.add(driverDto);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driverList;
    }

    @Override
    public List<Driver> findAllByExperience(Experience experience) {
        List<Driver> driverList = new ArrayList<>();
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(
                "SELECT * FROM track AS t " +
                        "RIGHT JOIN Driver AS d " +
                        "ON t.driver_fk_id = d.driver_id " +
                        "WHERE d.qualification = ?"
        )) {
            statement.setString(1, experience.name());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("track_id");
                int modelYear = resultSet.getInt("model_year");
                String model = resultSet.getString("model");

                int driverId = resultSet.getInt("driver_id");
                int driverAge = resultSet.getInt("driver_age");
                String driverName = resultSet.getString("driver_name");
                String driverQualification =
                        resultSet.getString("qualification");

                final Experience experience1 = Experience.valueOf(driverQualification);

                final Driver driver = new Driver(driverId, driverName, driverAge, experience1);

                final Track track = new Track(id, modelYear, model, driver);

                driver.addTrack(track);

                driverList.add(driver);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driverList;
    }

    @Override
    public void updateExperienceByName(String name, String exp) {
        Connection connection = ConnectionFactory.getConnection();
        String query = "UPDATE driver SET qualification=CAST (? AS enum_qualification) WHERE driver_name=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, exp);
            statement.setString(2, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Driver> findAllByNumberOfTrucks(int number) {
         List<Driver> driverList = new ArrayList<>();

        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(
                "SELECT dr.* from driver dr " +
                        "inner join (select count(t.track_id) as cnt,t.driver_fk_id "+
                        "from track t "+
                        "group by driver_fk_id "+
                        "having count(t.track_id)>=?) as stat "+
                        " on dr.driver_id=stat.driver_fk_id ")){
            statement.setInt(1, number);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int driverId = resultSet.getInt("driver_id");
                int driverAge = resultSet.getInt("driver_age");
                String driverName = resultSet.getString("driver_name");
                String driverQualification =
                        resultSet.getString("qualification");

                final Experience experience1 = Experience.valueOf(driverQualification);

                final Driver driver = new Driver(driverId, driverName, driverAge, experience1);

                driverList.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return driverList;
    }

}
