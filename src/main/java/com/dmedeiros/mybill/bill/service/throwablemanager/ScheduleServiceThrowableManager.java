package com.dmedeiros.mybill.bill.service.throwablemanager;

import com.dmedeiros.mybill.bill.exception.ScheduleEmptyException;
import com.dmedeiros.mybill.bill.model.Schedule;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceThrowableManager implements ServiceThrowableManager<Schedule> {


    @Override
    public void check(Schedule schedule) throws ScheduleEmptyException {
        if (schedule.isEmpty())
            throw new ScheduleEmptyException();
    }

}
