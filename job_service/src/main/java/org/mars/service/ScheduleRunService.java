package org.mars.service;


import lombok.RequiredArgsConstructor;
import org.mars.entity.Task;
import org.mars.repository.TaskDependencyRepository;
import org.mars.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleRunService {
    private final TaskDependencyRepository depRepo;
    private final TaskRepository taskRepo;
    private final TaskRunServiceV2 runService;
    private final RedisService redisService;


    public void runSchedule(Long scheduleId) {
        // Lấy task không có dependency
        List<Task> tasks = taskRepo.findTasksWithoutDependency(scheduleId);
        if (tasks.isEmpty()) {
            throw new IllegalStateException("No tasks found for scheduleId = " + scheduleId);
        }

        for (Task task : tasks) {
            runService.triggerQuartzJob(scheduleId, task.getId());
            redisService.setInterrupted(scheduleId, task.getId(), false);
        }
    }
}
