package com.group7.fruitswebsite.model;

import lombok.Data;

/**
 * @author duyenthai
 */
@Data
public class ChangeRoleModel {
    private Integer userId;
    private String oldRole;
    private String newRole;
}
