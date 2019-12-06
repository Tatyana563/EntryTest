package com.repository.postgre;

import com.domain.Driver;
import com.domain.Experience;
import com.domain.Track;
import com.dto.DriverDTO;

import java.util.List;

public interface DriverDAO {
    void save(Driver driver);

    void update(Driver driver);

    void deleteById(int driverId);

    List<Driver> findAllByExperience(Experience experience);

    void updateExperienceByName(String name, String exp);

    public List<Driver> findAllByNumberOfTrucks(int number);

    List<DriverDTO> findAllOnlyDriversByExperience(Experience experience);


}

