package com.ineor.test.jsonEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Result {
    private String countryName;
    private Double standard;
    private LocalDate date;

    public Result(String countryName, Double standard, LocalDate date) {
        this.countryName = countryName;
        this.standard = standard;
        this.date = date;
    }

    public Double getStandard() {
        return standard;
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public String getCountryName() {
        return countryName;
    }
}
