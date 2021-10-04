package com.group7.fruitswebsite.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author duyenthai
 */
@Setter
@Getter
@NoArgsConstructor
public abstract class BaseModel {
    private Integer id;
    private boolean status;
    private Integer updatedBy;
    private Integer createdBy;
}
