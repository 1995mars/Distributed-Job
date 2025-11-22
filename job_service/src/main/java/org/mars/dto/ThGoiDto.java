package org.mars.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThGoiDto {
    private Long thuebaoId;
    private String tenTb;
    private String email;
    private Double tien;
    private Double vat;
}
