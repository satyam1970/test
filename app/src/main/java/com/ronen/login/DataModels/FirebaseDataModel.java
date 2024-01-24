package com.ronen.login.DataModels;

public class FirebaseDataModel {
    private String name;
    private String city;
    private int Age;

    public FirebaseDataModel(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.Age = age;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return Age;
    }
}
