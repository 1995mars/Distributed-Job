package org.mars.job;


import org.mars.entity.ScheduleV1;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.lang.reflect.Method;

public class TaskJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduleV1 schedule = (ScheduleV1) context.getMergedJobDataMap().get("schedule");
        try {
            Class<?> clazz = Class.forName(schedule.getClassName());
            Method method = clazz.getDeclaredMethod(schedule.getMethodName());
            method.setAccessible(true);
            Object obj = clazz.getDeclaredConstructor().newInstance();
            method.invoke(obj);
            System.out.println("Executed job: " + schedule.getClassName() + "." + schedule.getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            throw new JobExecutionException(e);
        }
    }



}
