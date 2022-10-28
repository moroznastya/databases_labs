package com.moroz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Appointment {
    private Integer id;
    private String dateTime;
    private Integer serviceId;
    private Integer doctorId;
    private Integer patientId;
    private Integer patientClientId;
    private Integer clinicId;
}
