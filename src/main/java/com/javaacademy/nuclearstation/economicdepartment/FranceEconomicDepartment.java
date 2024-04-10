package com.javaacademy.nuclearstation.economicdepartment;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.ZERO;

@Component
@Profile("france")
public class FranceEconomicDepartment extends EconomicDepartment {

    @Override
    public BigDecimal computeYearIncomes(long countElectricity) {
        BigDecimal totalIncome = ZERO;
        long qtyElectricity = 1_000_000_000L;
        double coefficient = 0.99;

        int count = 0;
        while (countElectricity > 0) {
            if (countElectricity > qtyElectricity) {
                totalIncome = totalIncome.add(tariff(qtyElectricity, baseRate, coefficient, count));
                countElectricity -= qtyElectricity;
            } else {
                totalIncome = totalIncome.add(tariff(countElectricity, baseRate, coefficient, count));
                countElectricity = 0;
            }
            count++;
        }
        return totalIncome.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal tariff(long countElectricity, BigDecimal baseRate, double coefficient, int count) {
        return BigDecimal.valueOf(countElectricity).multiply(calculate(baseRate, coefficient, count));
    }

    private BigDecimal calculate(BigDecimal baseRate, double coefficient, int count) {
        return baseRate.multiply(calculateCoefficient(coefficient, count));
    }

    private BigDecimal calculateCoefficient(double coefficient, int count) {
        if (count == 0) {
            return BigDecimal.ONE;
        } else {
            return BigDecimal.valueOf(coefficient).pow(count);
        }
    }
}
