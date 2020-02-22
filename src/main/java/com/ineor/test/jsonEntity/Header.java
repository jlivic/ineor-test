package com.ineor.test.jsonEntity;

import java.util.List;

public class Header {
    private String details;
    private String version;
    private List<HeaderRate> rates;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<HeaderRate> getRates() {
        return rates;
    }

    public void setRates(List<HeaderRate> rates) {
        this.rates = rates;
    }
}
