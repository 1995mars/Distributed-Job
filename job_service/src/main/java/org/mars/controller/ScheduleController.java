package org.mars.controller;//package com.mars.OneCV.controller;
//
//
//import com.mars.OneCV.entity.Schedule;
//import com.mars.OneCV.service.ScheduleService;
//import org.quartz.SchedulerException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/api/schedules")
//public class ScheduleController {
//
//    @Autowired
//    private ScheduleService scheduleService;
//
//    @GetMapping("")
//    public ResponseEntity<List<Schedule>> getAllSchedules() {
//        List<Schedule> schedules = scheduleService.getAllSchedules();
//        return new ResponseEntity<>(schedules, HttpStatus.OK);
//    }
//
//    @PostMapping("/add")
//    public Schedule add(@RequestBody Schedule schedule) throws SchedulerException {
//        scheduleService.addSchedule(schedule);
//        return schedule;
//    }
//
//    @PostMapping("/edit")
//    public Schedule edit(@RequestBody Schedule schedule) throws SchedulerException {
//        scheduleService.updateSchedule(schedule);
//        return schedule;
//    }
//
//    @GetMapping("/delete/{id}")
//    public ResponseEntity<Object> delete(@PathVariable("id") Long id) throws SchedulerException {
//        scheduleService.removeSchedule(id);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/pause/{id}")
//    public ResponseEntity<Object> pause(@PathVariable("id") Long id) throws SchedulerException {
//        scheduleService.pauseSchedule(id);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/stop/{id}")
//    public ResponseEntity<Object> stop(@PathVariable("id") Long id) throws SchedulerException {
//        scheduleService.stopScheduleV2(id);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/resume/{id}")
//    public ResponseEntity<Object> resume(@PathVariable("id") Long id) throws SchedulerException {
//        scheduleService.resumeSchedule(id);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/runDynamicJob")
//    public ResponseEntity<String> runDynamicJob(@RequestBody Map body) {
//        try {
//            scheduleService.runJobNow((Long) body.get("id"));
//            return ResponseEntity.ok("Triggered successfully!");
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Error: " + e.getMessage());
//        }
//    }
//
//    @PostMapping("/runDynamicJoV2")
//    public ResponseEntity<String> runDynamicJobV2(@RequestBody Schedule schedule) {
//        try {
//            scheduleService.runJobNowV2(schedule);
//            return ResponseEntity.ok("Triggered successfully!");
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Error: " + e.getMessage());
//        }
//    }
//
//}