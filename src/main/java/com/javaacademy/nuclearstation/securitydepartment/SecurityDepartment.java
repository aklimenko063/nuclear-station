package com.javaacademy.nuclearstation.securitydepartment;

import com.javaacademy.nuclearstation.NuclearStation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class SecurityDepartment {
    @Autowired
    @Lazy
    private NuclearStation nuclearStation;
    private int accidentCountPeriod = 0;

    public void addAccident() {
        accidentCountPeriod += 1;
    }

    public void reset() {
        nuclearStation.incrementAccident(accidentCountPeriod);
        accidentCountPeriod = 0;
    }
}
