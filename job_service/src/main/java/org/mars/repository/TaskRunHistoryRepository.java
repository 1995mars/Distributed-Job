package org.mars.repository;

import org.mars.entity.TaskRunHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRunHistoryRepository extends JpaRepository<TaskRunHistory, Long> {

    List<TaskRunHistory> findByTaskId(Long taskId);

    List<TaskRunHistory> findByScheduleId(Long scheduleId);

    List<TaskRunHistory> findByTaskIdOrderByStartTimeDesc(Long taskId);

    // 🔹 Kiểm tra xem 1 task đã có lịch sử chạy thành công chưa
    boolean existsByScheduleIdAndTaskIdAndStatus(Long scheduleId, Long taskId, String status);
}
