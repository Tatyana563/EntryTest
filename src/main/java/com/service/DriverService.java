package com.service;

import com.domain.Driver;
import com.domain.Experience;
import com.dto.DriverDTO;

import java.util.List;

public interface DriverService<T> extends CRUDService<T> {

    List<Driver> findAllByExperience(Experience experience);

    void updateExperienceByName(String name, String exp);

    List<DriverDTO> findAllOnlyDriversByExperience(Experience experience);

     List<Driver> findAllByNumberOfTrucks(int number);

void updateQualificationByWorkload(int number, int modelYear);
}
