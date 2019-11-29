package com.repository.postgre;

import com.domain.Driver;

import java.util.List;

public interface DriverDAO {
    void save(Driver driver);
    void update(Driver driver);
    void deleteById(int driverId);
    List<Driver> findAllByExperience(String experience);
}
