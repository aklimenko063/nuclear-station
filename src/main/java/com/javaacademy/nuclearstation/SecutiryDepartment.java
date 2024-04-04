package com.javaacademy.nuclearstation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class SecutiryDepartment {
    @Autowired
    @Lazy
    private NuclearStation nuclearStation;
    private int accidentCountPeriod = 0;

    public void addAccident() {
        accidentCountPeriod += 1;
    }

    public int getCountAccidents() {
        return accidentCountPeriod;
    }

    public void reset() {
        nuclearStation.incrementAccident(accidentCountPeriod);
        accidentCountPeriod = 0;
    }
}
