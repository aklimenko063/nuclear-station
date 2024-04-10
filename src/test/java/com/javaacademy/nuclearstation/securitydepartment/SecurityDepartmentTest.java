package com.javaacademy.nuclearstation.securitydepartment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SecurityDepartmentTest {
	@Autowired
	private SecurityDepartment securityDepartment;

	@Test
	void addAccidentSuccess() {
		long expected = 1;
		securityDepartment.setAccidentCountPeriod(0);
		securityDepartment.addAccident();
		Assertions.assertEquals(expected, securityDepartment.getAccidentCountPeriod());
	}

	@Test
	void addAccidentDoesNotThrowSuccess() {
		Assertions.assertDoesNotThrow(() -> securityDepartment.addAccident());
	}

	@Test
	void resetSuccess() {
		long expected = 0;
		securityDepartment.setAccidentCountPeriod(1);
		securityDepartment.reset();
		Assertions.assertEquals(expected, securityDepartment.getAccidentCountPeriod());
	}

	@Test
	void resetDoesNotThrowSuccess() {
		Assertions.assertDoesNotThrow(() -> securityDepartment.reset());
	}
}
