package com.group7.fruitswebsite.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author duyenthai
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public abstract class BaseModel {
    private Integer id;
    private boolean status;
    @JsonProperty(value = "updated_by")
    private Integer updatedBy;
    @JsonProperty(value = "created_by")
    private Integer createdBy; 
}
