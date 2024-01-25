package com.ronen.login.DataModels;

public class FirebaseDataModel {
    private String name;
    private String city;
    private int age;

    public FirebaseDataModel(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    public FirebaseDataModel() {
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAge(int age) {
        age = age;
    }
}
