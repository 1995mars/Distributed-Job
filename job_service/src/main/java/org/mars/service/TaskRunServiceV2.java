package org.mars.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mars.batch.TaskRunner;
import org.mars.entity.Task;
import org.mars.entity.TaskRunHistory;
import org.mars.job.TaskJobV2;
import org.mars.repository.TaskDependencyRepository;
import org.mars.repository.TaskRepository;
import org.mars.repository.TaskRunHistoryRepository;
import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class TaskRunServiceV2 {
    private final Scheduler scheduler;
    private final TaskRepository taskRepo;
    private final TaskDependencyRepository depRepo;
    private final TaskRunHistoryRepository historyRepo;
    private final ApplicationContext context;
    private final RedisService redisService;

    @Transactional
    public void executeTask(Long scheduleId, Long taskId) {
        Task task = taskRepo.findById(taskId).orElseThrow();
        TaskRunHistory history = new TaskRunHistory(scheduleId, taskId, LocalDateTime.now(), "RUNNING");
        historyRepo.save(history);

        boolean isInterrupted = redisService.checkInterrupted(scheduleId, taskId);

        try {
            TaskRunner runner = (TaskRunner) context.getBean(Class.forName(task.getClassName()));
            runner.runTask(() -> isInterrupted); 
            history.setStatus(isInterrupted ? "STOPPED" : "SUCCESS");
        } catch (Exception e) {
            history.setStatus("FAILED");
            history.setMessage(e.getMessage());
        } finally {
            history.setEndTime(LocalDateTime.now());
            historyRepo.save(history);
        }

        if (isInterrupted) {
            triggerNextTasks(scheduleId, taskId);
        }
    }


    private void triggerNextTasks(Long scheduleId, Long completedTaskId) {
        List<Long> nextTaskIds = depRepo.findTaskIdsThatDependOn(scheduleId, completedTaskId);

        for (Long nextId : nextTaskIds) {
            if (allDependenciesDone(scheduleId, nextId)) {
                triggerQuartzJob(scheduleId, nextId);
            }
        }
    }

    private boolean allDependenciesDone(Long scheduleId, Long taskId) {
        List<Long> depIds = depRepo.findDependsOnIds(scheduleId, taskId);
        return depIds.stream()
                .allMatch(depId -> historyRepo.existsByScheduleIdAndTaskIdAndStatus(scheduleId, depId, "SUCCESS"));
    }

    void triggerQuartzJob(Long scheduleId, Long taskId) {
        try {
            JobDetail job = JobBuilder.newJob(TaskJobV2.class)
                    .withIdentity("task_" + taskId, "schedule_" + scheduleId)
                    .usingJobData("scheduleId", scheduleId)
                    .usingJobData("taskId", taskId)
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger_" + taskId, "schedule_" + scheduleId)
                    .startNow()
                    .build();

            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}
