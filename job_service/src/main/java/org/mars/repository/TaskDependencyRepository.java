package org.mars.repository;


import org.mars.entity.TaskDependency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDependencyRepository extends JpaRepository<TaskDependency, Long> {

    @Query("SELECT td.taskId FROM TaskDependency td WHERE td.scheduleId = :scheduleId AND td.dependsOnTaskId = :completedTaskId")
    List<Long> findTaskIdsThatDependOn(Long scheduleId, Long completedTaskId);

    @Query("SELECT td.dependsOnTaskId FROM TaskDependency td WHERE td.scheduleId = :scheduleId AND td.taskId = :taskId")
    List<Long> findDependsOnIds(Long scheduleId, Long taskId);
}