package com.javaacademy.nuclearstation.reactordepartment;

import com.javaacademy.nuclearstation.securitydepartment.SecurityDepartment;
import com.javaacademy.nuclearstation.reactordepartment.exceptions.NuclearFuelsEmptyException;
import com.javaacademy.nuclearstation.reactordepartment.exceptions.ReactorWorkException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@Slf4j
public class ReactorDepartment {
    private SecurityDepartment securityDepartment;
    private boolean isWorkNow;
    private int runCount;
    @Value("${nuclear-station.performance}")
    private long performance;

    public ReactorDepartment(SecurityDepartment securityDepartment) {
        this.securityDepartment = securityDepartment;
    }

    public long run() throws ReactorWorkException, NuclearFuelsEmptyException {
        if (isWorkNow) {
            addAccident();
            throw new ReactorWorkException("Реактор уже работает. Запуск невозможен!");
        }
        if (runCount >= 100) {
            addAccident();
            throw new NuclearFuelsEmptyException("Закончилось топливо в реакторе. Запуск невозможен!");
        }
        this.isWorkNow = true;
        this.runCount++;
        return this.performance;
    }

    public void stop() throws ReactorWorkException {
        if (!isWorkNow) {
            addAccident();
            throw new ReactorWorkException("Реактор уже выключен.");
        }
        this.isWorkNow = false;
    }

    public void refuelTheReactor() {
        runCount = 0;
    }

    private void addAccident() {
        securityDepartment.addAccident();
        log.warn("Внимание! Происходят работы на атомной станции! Электричества нет!");
    }
}
