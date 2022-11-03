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
@Relation(itemRelation = "clinic", collectionRelation = "clinics")
public class ClinicDto extends RepresentationModel<ClinicDto> {
    private final Integer id;
    private final String name;
    private final String clinicPhone;
    private final String streetAddress;
}
