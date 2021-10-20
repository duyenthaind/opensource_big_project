package com.group7.fruitswebsite.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author duyenthai
 */
@NoArgsConstructor
@Setter
@Getter
public abstract class BaseDto {
    private Integer id;

    private Boolean status = Boolean.TRUE;

    @JsonProperty(value = "updated_by")
    private Integer updatedBy;

    @JsonProperty(value = "created_by")
    private Integer createdBy;

    @JsonProperty(value = "updated_date")
    private Long updatedDate;

    @JsonProperty(value = "created_date")
    private Long createdDate;
}
