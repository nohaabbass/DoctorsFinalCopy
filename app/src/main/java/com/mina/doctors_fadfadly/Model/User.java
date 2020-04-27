package com.mina.doctors_fadfadly.Model;

public class User {
    String name;
    String id;
    String password;
    String email;
    String age;
    String gender;
    String nationalId;
    String address;
    String bachelor;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() { return age; }

    public void setAge(String age) { this.age = age; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getNationalId() { return nationalId; }

    public void setNationalId(String nationalId) { this.nationalId = nationalId; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getBachelor() { return bachelor; }

    public void setBachelor(String bachelor) { this.bachelor = bachelor; }

    public User() {
    }
}