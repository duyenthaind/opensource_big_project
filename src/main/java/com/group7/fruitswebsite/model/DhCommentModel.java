package com.group7.fruitswebsite.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author duyenthai
 */
@Getter
@Setter
@ToString
public class DhCommentModel extends BaseModel {
    private String message;
    private Integer productId;
    private Integer userId;
    private Integer parentId;
}
