package org.mars.service;

import java.time.Duration;

import org.mars.constant.TaskConstant;
import org.mars.entity.Task;
import org.mars.repository.TaskRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final StringRedisTemplate redisTemplate;

    public boolean isTaskInterrupted(Long taskId) {
        String key = TaskConstant.KEY_PREFIX + ":" + taskId;

        //Check key is exits in redis or not
        String cacheValue = redisTemplate.opsForValue().get(key);
        if (cacheValue != null) {
            return Boolean.parseBoolean(cacheValue);
        }

        //If key is not exits -> get from db
        Task task = taskRepository.findById(taskId).orElseThrow(
            () -> new RuntimeException("Task is not found")
        );
        boolean status = task.getIsInterrupted();
        //Sync to redis
        redisTemplate.opsForValue().set(key, String.valueOf(status), Duration.ofHours(1));

        return status;
    }

    @Transactional
    public void updateTaskInterrupted(Long taskId, boolean isInterrupted) {
        Task task = taskRepository.findById(taskId).orElseThrow(
            () -> new RuntimeException("Task is not found")
        ); 

        task.setIsInterrupted(isInterrupted);
        taskRepository.save(task);

        String key = TaskConstant.KEY_PREFIX + ":" + taskId;
        redisTemplate.opsForValue().set(key, String.valueOf(isInterrupted), Duration.ofHours(1));
    }
}
