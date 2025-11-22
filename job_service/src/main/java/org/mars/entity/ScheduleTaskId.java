package org.mars.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ScheduleTaskId implements Serializable {
    private Long scheduleId;
    private Long taskId;
}
