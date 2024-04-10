package com.javaacademy.nuclearstation.economicdepartment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

@SpringBootTest
public class DefaultEconomicDepartmentTest {
	@Autowired
	private DefaultEconomicDepartment defaultEconomicDepartment;

	@Test
	void computeYearIncomesSuccess() {
		BigDecimal expected = ZERO;
		Assertions.assertEquals(expected, defaultEconomicDepartment.computeYearIncomes(100));
	}

	@Test
	void computeYearIncomesDoesNotThrowSuccess() {
		Assertions.assertDoesNotThrow(() -> defaultEconomicDepartment.computeYearIncomes(100));
	}
}
