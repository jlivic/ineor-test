package com.ineor.test.jsonEntity;

import java.util.List;

/**
 * JSON Header mapper
 * @author Janko Livic
 */
public class HeaderRate {
    private String name;
    private String code;
    private String country_code;
    private List<HeaderRatePeriod> periods;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public List<HeaderRatePeriod> getPeriods() {
        return periods;
    }

    public void setPeriods(List<HeaderRatePeriod> periods) {
        this.periods = periods;
    }
}
