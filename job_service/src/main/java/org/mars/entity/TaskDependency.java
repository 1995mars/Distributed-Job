package org.mars.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "task_dependency")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDependency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "schedule_id")
    private Long scheduleId;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "depends_on_task_id")
    private Long dependsOnTaskId;
}
