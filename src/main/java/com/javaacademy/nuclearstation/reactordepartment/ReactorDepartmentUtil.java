package com.javaacademy.nuclearstation.reactordepartment;

import com.javaacademy.nuclearstation.reactordepartment.exceptions.NuclearFuelsEmptyException;
import com.javaacademy.nuclearstation.reactordepartment.exceptions.ReactorWorkException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReactorDepartmentUtil {

    void checkTheConditionOfTheReactorStart(boolean condition) throws ReactorWorkException {
        if (condition) {
            throw new ReactorWorkException("Реактор уже работает. Запуск невозможен!");
        }
    }

    void checkTheConditionOfTheReactorStop(boolean condition) throws ReactorWorkException {
        if (!condition) {
            throw new ReactorWorkException("Реактор уже выключен.");
        }
    }

    public static void checkTheFuelInTheReactor(int runCount) throws NuclearFuelsEmptyException {
        if (runCount >= 100) {
            throw new NuclearFuelsEmptyException("Закончилось топливо в реакторе. Запуск невозможен!");
        }
    }
}
