package com.ineor.test.jsonEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Rate mapper
 * @author Janko Livic
 */
public class PeriodRate {
    private Double standard;

    public Double getStandard() {
        return standard;
    }

    public void setStandard(Double standard) {
        this.standard = standard;
    }
}
