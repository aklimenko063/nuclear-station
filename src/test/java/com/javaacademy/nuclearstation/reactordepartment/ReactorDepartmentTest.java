package com.javaacademy.nuclearstation.reactordepartment;

import com.javaacademy.nuclearstation.reactordepartment.exceptions.NuclearFuelsEmptyException;
import com.javaacademy.nuclearstation.reactordepartment.exceptions.ReactorWorkException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ReactorDepartmentTest {
	@Autowired
	private ReactorDepartment reactorDepartment;

	@Test
	void runSuccess() throws ReactorWorkException, NuclearFuelsEmptyException {
		long expected = 10_000_000;
		reactorDepartment.setWorkNow(false);
		reactorDepartment.setRunCount(0);
		Assertions.assertEquals(expected, reactorDepartment.run());
	}

	@Test
	void runThrowReactorWorkExceptionSuccess() throws ReactorWorkException, NuclearFuelsEmptyException {
		reactorDepartment.setWorkNow(true);
		Assertions.assertThrows(ReactorWorkException.class, () -> reactorDepartment.run());
	}

	@Test
	void runThrowNuclearFuelsEmptyExceptionSuccess() throws ReactorWorkException, NuclearFuelsEmptyException {
		reactorDepartment.setRunCount(100);
		reactorDepartment.setWorkNow(false);
		Assertions.assertThrows(NuclearFuelsEmptyException.class, () -> reactorDepartment.run());
	}

	@Test
	void runDoesNotThrowSuccess() {
		reactorDepartment.setWorkNow(false);
		reactorDepartment.setRunCount(0);
		Assertions.assertDoesNotThrow(() -> reactorDepartment.run());
	}

	@Test
	void stopSuccess() throws ReactorWorkException {
		reactorDepartment.setWorkNow(true);
		reactorDepartment.stop();
		Assertions.assertFalse(reactorDepartment.isWorkNow());
	}

	@Test
	void stopDoesNotThrowSuccess() throws ReactorWorkException {
		reactorDepartment.setWorkNow(true);
		Assertions.assertDoesNotThrow(() -> reactorDepartment.stop());
	}

	@Test
	void stopThrowReactorWorkExceptionSuccess() throws ReactorWorkException {
		reactorDepartment.setWorkNow(false);
		Assertions.assertThrows(ReactorWorkException.class, () -> reactorDepartment.stop());
	}

	@Test
	void refuelTheReactorDoesNotThrowSuccess() {
		Assertions.assertDoesNotThrow(() -> reactorDepartment.refuelTheReactor());
	}

	@Test
	void refuelTheReactorSuccess() {
		long expected = 0;
		reactorDepartment.refuelTheReactor();
		Assertions.assertEquals(expected, reactorDepartment.getRunCount());
	}
}
