package org.mars.controller;



import org.mars.service.ScheduleRunService;
import org.quartz.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/test")
public class TestController {


    private final ScheduleRunService scheduleRunService;

    public TestController(JobLauncher jobLauncher, ScheduleRunService scheduleRunService) {
        this.scheduleRunService = scheduleRunService;
    }


    @PostMapping("/run")
    public ResponseEntity<String> run() throws SQLException, IOException {
        scheduleRunService.runSchedule(1L);
        return ResponseEntity.ok("RunJob thành công  ");
    }

}