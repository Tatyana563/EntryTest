package com.dto;

import com.domain.Driver;
import com.domain.Experience;

public class DriverDTO {
    private int id;
    private String name;
    private int age;
    private Experience experience;
//reference
    public DriverDTO(Driver driver) {
        this.id = driver.getId();
        this.name = driver.getName();
        this.age = driver.getAge();
        this.experience = driver.getExperience();
    }
//for Jackson const setter/getter required, reflection newIntance() method
    public DriverDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }
}
