package com.moroz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Service {
    private Integer id;
    private String name;
    private String duration;
    private String isAvailable;
    private Integer doctorId;
}
