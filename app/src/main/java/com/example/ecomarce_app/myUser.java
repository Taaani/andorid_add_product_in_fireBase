package com.example.ecomarce_app;

public class myUser {

    String fname , city;
    int age;

    myUser(){


    };
    myUser(String n, String c, int a){

        this.fname = n;
        this.city = c;
        this.age = a;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
