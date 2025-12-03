package org.mars.job;

import org.mars.service.TaskRunServiceV2;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class TaskJobV2 implements InterruptableJob {
    private volatile boolean interrupted = false;

    @Autowired
    private ApplicationContext context;

    @Override
    public void execute(JobExecutionContext ctx) throws JobExecutionException {
        Long scheduleId = ctx.getMergedJobDataMap().getLong("scheduleId");
        Long taskId = ctx.getMergedJobDataMap().getLong("taskId");

        TaskRunServiceV2 service = context.getBean(TaskRunServiceV2.class);
        service.executeTask(scheduleId, taskId);
    }

    @Override
    public void interrupt() {
        interrupted = true;
        System.out.println("🔴 TaskJobV2 received interrupt signal");
    }
}
