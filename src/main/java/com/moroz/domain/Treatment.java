package com.moroz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Treatment {
    private Integer id;
    private String description;
    private String recommendation;
    private String diagnosisName;
    private Integer patientId;
}
