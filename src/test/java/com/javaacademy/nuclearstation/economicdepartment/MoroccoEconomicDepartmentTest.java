package com.javaacademy.nuclearstation.economicdepartment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SpringBootTest
@ActiveProfiles("morocco")
public class MoroccoEconomicDepartmentTest {
	@Autowired
	private MoroccoEconomicDepartment moroccoEconomicDepartment;

	@Test
	void computeYearIncomesTestOneSuccess() {
		BigDecimal expected = BigDecimal.valueOf(18_100_000_000.00).setScale(2, RoundingMode.HALF_UP);
		Assertions.assertEquals(expected, moroccoEconomicDepartment.computeYearIncomes(3_620_000_000L));
	}

	@Test
	void computeYearIncomesTestTwoSuccess() {
		BigDecimal expected = BigDecimal.valueOf(31_000_000_000.00).setScale(2, RoundingMode.HALF_UP);
		Assertions.assertEquals(expected, moroccoEconomicDepartment.computeYearIncomes(6_000_000_000L));
	}

	@Test
	void computeYearIncomesDoesNotThrowSuccess() {
		Assertions.assertDoesNotThrow(() -> moroccoEconomicDepartment.computeYearIncomes(3_600_000_000L));
	}
}
