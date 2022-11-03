package com.moroz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patient {
    private Integer id;
    private String breed;
    private String healthComplains;
    private Integer clientId;
}
