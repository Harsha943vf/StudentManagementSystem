package com.sms.model;

import java.sql.Date;

public class PartTimeStudent extends Student {

    private double hourlyRate;
    private int hours;

    public PartTimeStudent(int id, String fn, String ln, Date doj,
                            double hourlyRate, int hours) {
        super(id, fn, ln, doj);
        this.hourlyRate = hourlyRate;
        this.hours = hours;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public int getHours() {
        return hours;
    }

    @Override
    public String getType() {
        return "PART";
    }
}
