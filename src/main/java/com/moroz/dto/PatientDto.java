package com.moroz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "doctor", collectionRelation = "doctors")
public class PatientDto extends RepresentationModel<PatientDto> {
    private final Integer id;
    private final String breed;
    private final String healthComplains;
}
