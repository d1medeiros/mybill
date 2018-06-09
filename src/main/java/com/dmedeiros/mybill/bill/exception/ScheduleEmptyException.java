package com.dmedeiros.mybill.bill.exception;

public class ScheduleEmptyException extends RuntimeException {
    public ScheduleEmptyException() {
        super("Schedule was not filled.");
    }
}
