package com.service;

import com.domain.Driver;

import java.util.List;

public interface DriverService<T> {
    void save(T t);

    List<Driver> findAllByExperience(String experience);

    void deleteById(int driverId);
}
