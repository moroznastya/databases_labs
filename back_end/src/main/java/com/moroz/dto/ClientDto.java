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
@Relation(itemRelation = "client", collectionRelation = "clients")
public class ClientDto extends RepresentationModel<CityDto> {
    private final Integer id;
    private final String name;
    private final String surname;
    private final String contactNumber;
}
