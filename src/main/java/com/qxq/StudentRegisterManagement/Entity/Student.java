package com.qxq.StudentRegisterManagement.Entity;

public class Student {
    
    private int id;
    private String name;
    private int phoneNum;
    private Address address;

    public Student() {
    }

    public Student(int id, String name, int phoneNum) {
        this.id = id;
        this.name = name;
        this.phoneNum = phoneNum;
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

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }
    public Address getAddress(){
        return address;
    }
    public void setAddress(Address address){
        this.address = address;
    }
}
