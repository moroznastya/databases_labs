package com.moroz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Clinic {
    private Integer id;
    private String name;
    private String clinicPhone;
    private String streetAddress;
    private String city;
}
