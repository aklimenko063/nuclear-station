package com.javaacademy.nuclearstation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NuclearStationTest {
	@Autowired
	private NuclearStation nuclearStation;

	@Test
	void addAccidentDoesNotThrowSuccess() {
		Assertions.assertDoesNotThrow(() -> nuclearStation.start(3));
	}

	@Test
	void incrementAccidentSuccess() {
		long expected = 1;
		nuclearStation.setAccidentCountAllTime(0);
		nuclearStation.incrementAccident(1);
		Assertions.assertEquals(expected, nuclearStation.getAccidentCountAllTime());
	}
}
