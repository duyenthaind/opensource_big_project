package com.group7.fruitswebsite.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author duyenthai
 */
@Getter
@Setter
@ToString
public class DhBlogDto extends BaseDto{
    private String thumbnail;
    private String details;
    private String shortDescription;
    private String avatar;
}
