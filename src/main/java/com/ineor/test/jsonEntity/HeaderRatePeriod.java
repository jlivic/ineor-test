package com.ineor.test.jsonEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HeaderRatePeriod {

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate effective_from;
    @JsonProperty("rates")
    private PeriodRate rate;

    public LocalDate getEffective_from() {
        return effective_from;
    }

    public void setEffective_from(LocalDate effective_from) {
        this.effective_from = effective_from;
    }

    public PeriodRate getRate() {
        return rate;
    }

    public void setRate(PeriodRate rate) {
        this.rate = rate;
    }

    public Double getPeriodRateStandard(){
        return getRate().getStandard();
    }
}
