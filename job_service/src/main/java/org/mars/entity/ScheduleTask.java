package org.mars.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "schedule_task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ScheduleTaskId.class)
public class ScheduleTask {

    @Id
    @Column(name = "schedule_id")
    private Long scheduleId;

    @Id
    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "order_index")
    private Integer orderIndex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", insertable = false, updatable = false)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", insertable = false, updatable = false)
    private Task task;
}
