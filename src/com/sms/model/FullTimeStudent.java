package com.sms.model;

import java.sql.Date;

public class FullTimeStudent extends Student {

    private double salary;

    public FullTimeStudent(int id, String fn, String ln, Date doj, double salary) {
        super(id, fn, ln, doj);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String getType() {
        return "FULL";
    }
}
