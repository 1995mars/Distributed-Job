package org.mars.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ScheduleV1 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cronExpression;
    private boolean enabled;
    private LocalDateTime validStartDate;
    private LocalDateTime validEndDate;
    private String className;      // ví dụ: com.mars.scheduling.batch.VNEmployee
    private String methodName;     // ví dụ: birthdayBatch

}

