package com.service;

import com.domain.Driver;
import com.domain.Experience;

import java.util.List;

public interface DriverService<T> extends CRUDService<T> {

    List<Driver> findAllByExperience(Experience experience);

    void updateExperienceByName(String name, String exp);
}
