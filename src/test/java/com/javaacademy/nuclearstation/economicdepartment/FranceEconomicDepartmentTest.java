package com.javaacademy.nuclearstation.economicdepartment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SpringBootTest
@ActiveProfiles("france")
public class FranceEconomicDepartmentTest {
	@Autowired
	private FranceEconomicDepartment franceEconomicDepartment;

	@Test
	void computeYearIncomesSuccess() {
		BigDecimal expected = BigDecimal.valueOf(1_785_842_690.00).setScale(2, RoundingMode.HALF_UP);
		Assertions.assertEquals(expected, franceEconomicDepartment.computeYearIncomes(3620000000L));
	}

	@Test
	void computeYearIncomesDoesNotThrowSuccess() {
		Assertions.assertDoesNotThrow(() -> franceEconomicDepartment.computeYearIncomes(3_600_000_000L));
	}
}
