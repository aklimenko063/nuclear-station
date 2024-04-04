package com.javaacademy.nuclearstation;

import com.javaacademy.nuclearstation.economicdepartment.EconomicDepartment;
import com.javaacademy.nuclearstation.reactordepartment.ReactorDepartment;
import com.javaacademy.nuclearstation.reactordepartment.exceptions.NuclearFuelsEmptyException;
import com.javaacademy.nuclearstation.reactordepartment.exceptions.ReactorWorkException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.javaacademy.nuclearstation.Runner.DAYS_OF_YEAR;

@Component
@Getter
@Slf4j
public class NuclearStation {
    private ReactorDepartment reactorDepartment;
    private SecutiryDepartment secutiryDepartment;
    private EconomicDepartment economicDepartment;
    @Value("${economic_department.country}")
    private String country;
    private long totalAmountOfEnergyGenerated = 0;
    private long totalAmountOfYearEnergyGenerated = 0;
    private int accidentCountAllTime = 0;

    public NuclearStation(ReactorDepartment reactorDepartment,
                          SecutiryDepartment secutiryDepartment,
                          EconomicDepartment economicDepartment) {
        this.reactorDepartment = reactorDepartment;
        this.secutiryDepartment = secutiryDepartment;
        this.economicDepartment = economicDepartment;
    }

    public void start(int year) {
        log.info("Действие происходит в стране: {}", country);
        for (int yearCount = 1 ; yearCount <= year ; yearCount++) {
            startYear();
        }
        log.info("Атомная станция выработала всего {} киловатт/часов", totalAmountOfEnergyGenerated);
        log.info("Количество инцидентов за всю работу станции: {}", accidentCountAllTime);
    }

    public void incrementAccident(int count) {
        this.accidentCountAllTime += count;
    }

    private void startYear() {
        log.info("Атомная станция начала работу.");
        for (int day = 1 ; day <= DAYS_OF_YEAR ; day++) {
            try {
                long elaboration = reactorDepartment.run();
                reactorDepartment.stop();
                totalAmountOfYearEnergyGenerated += elaboration;
            } catch (ReactorWorkException e) {
                log.warn(e.getMessage());
                addAccident();
            } catch (NuclearFuelsEmptyException e) {
                log.warn(e.getMessage());
                addAccident();
                reactorDepartment.refuelTheReactor();
            }
        }
        totalAmountOfEnergyGenerated += totalAmountOfYearEnergyGenerated;
        log.info("Атомная станция закончила работу.");
        log.info("За год выработано {} киловатт/часов", totalAmountOfYearEnergyGenerated);
        log.info("Количество инцидентов за год: {}", secutiryDepartment.getCountAccidents());
        log.info("Доход за год составил: {} {}",
                economicDepartment.computeYearIncomes(totalAmountOfYearEnergyGenerated),
                economicDepartment.getCurrency());
        totalAmountOfYearEnergyGenerated = 0;
        secutiryDepartment.reset();
    }

    private void addAccident() {
        secutiryDepartment.addAccident();
        log.warn("Внимание! Происходят работы на атомной станции! Электричества нет!");
    }
}
