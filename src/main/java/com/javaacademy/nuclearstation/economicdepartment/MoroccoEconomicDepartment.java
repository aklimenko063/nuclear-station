package com.javaacademy.nuclearstation.economicdepartment;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.ZERO;

@Component
@Profile("morocco")
public class MoroccoEconomicDepartment extends EconomicDepartment {

    @Override
    public BigDecimal computeYearIncomes(long countElectricity) {
        BigDecimal totalIncome = ZERO;
        long qtyElectricity = 5_000_000_000L;
        BigDecimal coefficient = new BigDecimal(1);

        if (countElectricity > qtyElectricity) {
            totalIncome = totalIncome.add(baseRate.multiply(BigDecimal.valueOf(qtyElectricity)));
            countElectricity -= qtyElectricity;
            totalIncome = totalIncome.add(baseRate
                    .add(coefficient)
                    .multiply(BigDecimal.valueOf(countElectricity)))
                    .setScale(2, RoundingMode.HALF_UP);
        } else {
            totalIncome = totalIncome.add(baseRate.multiply(BigDecimal.valueOf(countElectricity)))
                    .setScale(2, RoundingMode.HALF_UP);
        }
        return totalIncome;
    }
}
