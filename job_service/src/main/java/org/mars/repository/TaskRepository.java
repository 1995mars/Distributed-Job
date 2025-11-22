package org.mars.repository;

import org.mars.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Tìm tất cả task trong 1 schedule
    @Query("""
        SELECT t
        FROM Task t
        WHERE t.id IN (
            SELECT st.taskId FROM ScheduleTask st WHERE st.scheduleId = :scheduleId
        )
        """)
    List<Task> findByScheduleId(Long scheduleId);

    // Tìm các task KHÔNG có dependency trong schedule
    @Query("""
        SELECT t
        FROM Task t
        WHERE t.id IN (
            SELECT st.taskId FROM ScheduleTask st WHERE st.scheduleId = :scheduleId
        )
        AND t.id NOT IN (
            SELECT td.taskId FROM TaskDependency td WHERE td.scheduleId = :scheduleId
        )
        """)
    List<Task> findTasksWithoutDependency(Long scheduleId);

}
