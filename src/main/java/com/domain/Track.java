package com.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Track {
    private int id;
    private int modelYear;
    private String model;
    private Driver driver;

    public Track(int id, int modelYear, String model, Driver driver) {
        this(modelYear, model);
        this.id = id;
        this.driver = driver;
    }

    public Track(int modelYear, String model) {
        this.modelYear = modelYear;
        this.model = model;
    }

    public Track() {
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", modelYear=" + modelYear +
                ", model='" + model + '\'' +
                '}';
    }
}

/*
* A() {
* new B()
* }
* B() {
* new A()
* }
* */
