package com.javaacademy.nuclearstation.reactordepartment;

import com.javaacademy.nuclearstation.SecutiryDepartment;
import com.javaacademy.nuclearstation.reactordepartment.exceptions.NuclearFuelsEmptyException;
import com.javaacademy.nuclearstation.reactordepartment.exceptions.ReactorWorkException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ReactorDepartment {
    private SecutiryDepartment secutiryDepartment;
    private boolean isWork;
    private int runCount;
    @Value("${nuclear-station.performance}")
    private long performance;

    public ReactorDepartment(SecutiryDepartment secutiryDepartment) {
        this.secutiryDepartment = secutiryDepartment;
    }

    public long run() throws ReactorWorkException, NuclearFuelsEmptyException {
        ReactorDepartmentUtil.checkTheConditionOfTheReactorStart(this.isWork);
        ReactorDepartmentUtil.checkTheFuelInTheReactor(this.runCount);
        return startReactor();
    }

    public void stop() throws ReactorWorkException {
        ReactorDepartmentUtil.checkTheConditionOfTheReactorStop(this.isWork);
        stopReactor();
    }

    public void refuelTheReactor() {
        runCount = 0;
    }

    private long startReactor() {
        this.isWork = true;
        this.runCount++;
        return performance;
    }

    private void stopReactor() {
        this.isWork = false;
    }
}
