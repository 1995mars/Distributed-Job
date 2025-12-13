package org.mars.repository;


import org.mars.entity.ScheduleTask;
import org.mars.entity.ScheduleTaskId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleTaskRepository extends JpaRepository<ScheduleTask, ScheduleTaskId> {
    List<ScheduleTask> findByScheduleId(Long scheduleId);
}