package com.service.impl;

import com.domain.Driver;
import com.domain.Experience;
import com.repository.postgre.DriverDAO;
import com.repository.postgre.PosgreDriverDAO;
import com.service.DriverService;

import java.util.List;

public class DriverServiceImpl implements DriverService<Driver> {
public static final DriverDAO DRIVER_DAO = new PosgreDriverDAO();

    @Override
    public void save(Driver driver) {
        DRIVER_DAO.save(driver);
    }

    @Override
    public List<Driver> findAllByExperience(Experience experience) {
        return DRIVER_DAO.findAllByExperience(experience);
    }

    @Override
    public void deleteById(int driverId) {
        DRIVER_DAO.deleteById(driverId);
    }

    @Override
    public void updateExperienceByName(String name, String exp) {
        DRIVER_DAO.updateExperienceByName(name,exp);
    }

    @Override
    public void update(Driver driver) {

    }
}
