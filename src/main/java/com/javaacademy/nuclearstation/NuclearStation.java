package com.javaacademy.nuclearstation;

import com.javaacademy.nuclearstation.economicdepartment.EconomicDepartment;
import com.javaacademy.nuclearstation.reactordepartment.ReactorDepartment;
import com.javaacademy.nuclearstation.reactordepartment.exceptions.NuclearFuelsEmptyException;
import com.javaacademy.nuclearstation.reactordepartment.exceptions.ReactorWorkException;
import com.javaacademy.nuclearstation.securitydepartment.SecurityDepartment;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@Slf4j
public class NuclearStation {
    private ReactorDepartment reactorDepartment;
    private SecurityDepartment securityDepartment;
    private EconomicDepartment economicDepartment;
    @Value("${nuclear-station.day_of_year}")
    private int dayOfYear;
    private long totalAmountOfEnergyGenerated = 0;
    private long totalAmountOfYearEnergyGenerated = 0;
    private int accidentCountAllTime = 0;

    public NuclearStation(ReactorDepartment reactorDepartment,
                          SecurityDepartment securityDepartment,
                          EconomicDepartment economicDepartment) {
        this.reactorDepartment = reactorDepartment;
        this.securityDepartment = securityDepartment;
        this.economicDepartment = economicDepartment;
    }

    public void start(int year) {
        log.info("Действие происходит в стране: {}", economicDepartment.getCountry());
        for (int yearCount = 1; yearCount <= year; yearCount++) {
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
        for (int day = 1; day <= dayOfYear; day++) {
            try {
                totalAmountOfYearEnergyGenerated += reactorDepartment.run();
                reactorDepartment.stop();
            } catch (ReactorWorkException e) {
                log.warn(e.getMessage());
            } catch (NuclearFuelsEmptyException e) {
                log.warn(e.getMessage());
                reactorDepartment.refuelTheReactor();
            }
        }
        totalAmountOfEnergyGenerated += totalAmountOfYearEnergyGenerated;
        log.info("Атомная станция закончила работу.");
        log.info("За год выработано {} киловатт/часов", totalAmountOfYearEnergyGenerated);
        log.info("Количество инцидентов за год: {}", securityDepartment.getAccidentCountPeriod());
        log.info("Доход за год составил: {} {}",
                economicDepartment.computeYearIncomes(totalAmountOfYearEnergyGenerated),
                economicDepartment.getCurrency());
        totalAmountOfYearEnergyGenerated = 0;
        securityDepartment.reset();
    }
}
