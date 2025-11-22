package org.mars.job;

import org.mars.entity.ScheduleV1;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

import java.lang.reflect.Method;

public class InterruptableTask implements InterruptableJob {

    private static volatile boolean interrupted = false;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            interrupted = false;
            var schedule = (ScheduleV1)
                    context.getMergedJobDataMap().get("schedule");

            Class<?> clazz = Class.forName(schedule.getClassName());
            Object obj = clazz.getDeclaredConstructor().newInstance();
            Method method = clazz.getDeclaredMethod(schedule.getMethodName());
            method.setAccessible(true);

            method.invoke(obj); // gọi code thực tế
        } catch (Exception e) {
            e.printStackTrace();
            throw new JobExecutionException(e);
        }
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        System.out.println("🛑 Job bị yêu cầu dừng!");
        interrupted = true;
    }

    public static boolean isInterrupted() {
        return interrupted;
    }

}