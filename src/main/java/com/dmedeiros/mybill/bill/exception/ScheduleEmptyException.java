package com.dmedeiros.mybill.bill.exception;

import com.dmedeiros.mybill.bill.model.Schedule;

public class ScheduleEmptyException extends BillEmptyException {

    public ScheduleEmptyException() {
        super(Schedule.class.getCanonicalName());
    }

}
