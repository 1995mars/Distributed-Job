package org.mars.controller;

import lombok.RequiredArgsConstructor;
import org.mars.service.TaskControlService;
import org.quartz.SchedulerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class TaskJobController {

    private final TaskControlService controlService;

    @PostMapping("/{scheduleId}/{taskId}/stop")
    public ResponseEntity<?> stop(@PathVariable Long scheduleId, @PathVariable Long taskId) throws SchedulerException, SchedulerException {
        controlService.stopJob(scheduleId, taskId);
        return ResponseEntity.ok("Stopped job " + taskId);
    }

    @PostMapping("/{scheduleId}/{taskId}/pause")
    public ResponseEntity<?> pause(@PathVariable Long scheduleId, @PathVariable Long taskId) throws SchedulerException {
        controlService.pauseJob(scheduleId, taskId);
        return ResponseEntity.ok("Paused job " + taskId);
    }

    @PostMapping("/{scheduleId}/{taskId}/resume")
    public ResponseEntity<?> resume(@PathVariable Long scheduleId, @PathVariable Long taskId) throws SchedulerException {
        controlService.resumeJob(scheduleId, taskId);
        return ResponseEntity.ok("Resumed job " + taskId);
    }
}

