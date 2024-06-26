package com.javaacademy.nuclearstation.economicdepartment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

@Component
@ConditionalOnMissingBean(value = {FranceEconomicDepartment.class, MoroccoEconomicDepartment.class})
@Slf4j
public class DefaultEconomicDepartment extends EconomicDepartment {

    @Override
    public BigDecimal computeYearIncomes(long countElectricity) {
        String message = "Ошибка расчета стоимости электроэнергии! Не указан профиль департамента для расчета.";
        log.error(message);
        return ZERO;
    }
}
