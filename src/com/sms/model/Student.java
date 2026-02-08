package com.sms.model;

import java.sql.Date;

public abstract class Student {

    protected int id;
    protected String firstName;
    protected String lastName;
    protected Date doj;

    public Student(int id, String firstName, String lastName, Date doj) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.doj = doj;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDoj() {
        return doj;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return id + " " + firstName + " " + lastName + " " + doj + " " + getType();
    }
}
