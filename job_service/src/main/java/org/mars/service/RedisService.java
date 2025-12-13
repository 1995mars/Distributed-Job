package org.mars.service;

import org.mars.constant.Constant;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final StringRedisTemplate redisTemplate;

    public boolean checkInterrupted(Long scheduleId, Long taskId) {
        String key = String.format(Constant.JOB_KEY_FORMAT, scheduleId, taskId);

        String cacheValue = redisTemplate.opsForValue().get(key);
        if (cacheValue != null) {
            return Boolean.parseBoolean(cacheValue);
        }

        setInterrupted(scheduleId, taskId, false);
        return false;
    }

    public void setInterrupted(Long scheduleId, Long taskId, boolean isInterrupted) {
        String key = String.format(Constant.JOB_KEY_FORMAT, scheduleId, taskId);
        redisTemplate.opsForValue().set(key, String.valueOf(isInterrupted));
    }
}
