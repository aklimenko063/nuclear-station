package com.javaacademy.nuclearstation.economicdepartment;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

@Getter
public abstract class EconomicDepartment {
    @Value("${economic_department.base_rate}")
    protected BigDecimal baseRate;
    @Value("${economic_department.currency}")
    protected String currency;

    public abstract BigDecimal computeYearIncomes(long countElectricity);
}
