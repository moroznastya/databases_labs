package com.moroz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Doctor {
    private Integer id;
    private String name;
    private String surname;
    private String workSchedule;
}