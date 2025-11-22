package org.mars.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "task_run_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRunHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "schedule_id")
    private Long scheduleId;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    private String status;

    @Lob
    private String message;

    public TaskRunHistory(Long scheduleId, Long taskId, LocalDateTime startTime, String status) {
        this.scheduleId = scheduleId;
        this.taskId = taskId;
        this.startTime = startTime;
        this.status = status;
    }
}
