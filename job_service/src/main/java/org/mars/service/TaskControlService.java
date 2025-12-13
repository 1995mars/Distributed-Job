package org.mars.service;

import lombok.RequiredArgsConstructor;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskControlService {

    private final Scheduler scheduler;
    private final RedisService redisService;

    public void stopJob(Long scheduleId, Long taskId) throws SchedulerException {
        JobKey jobKey = new JobKey("task_" + taskId, "schedule_" + scheduleId);
        redisService.setInterrupted(scheduleId, taskId, true);
        scheduler.interrupt(jobKey); // Gửi tín hiệu dừng
    }

    public void pauseJob(Long scheduleId, Long taskId) throws SchedulerException {
        JobKey jobKey = new JobKey("task_" + taskId, "schedule_" + scheduleId);
        scheduler.pauseJob(jobKey); // Tạm dừng job
    }

    public void resumeJob(Long scheduleId, Long taskId) throws SchedulerException {
        JobKey jobKey = new JobKey("task_" + taskId, "schedule_" + scheduleId);
        scheduler.resumeJob(jobKey); // Chạy lại job đã tạm dừng
    }

    public void deleteJob(Long scheduleId, Long taskId) throws SchedulerException {
        JobKey jobKey = new JobKey("task_" + taskId, "schedule_" + scheduleId);
        scheduler.deleteJob(jobKey); // Xóa hẳn khỏi Quartz
    }
}
