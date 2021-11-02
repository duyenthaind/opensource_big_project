package com.group7.fruitswebsite.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author duyenthai
 */
@Setter
@Getter
public class DhProductImageDto extends BaseDto{
    private String path;
    private String name;
    private int productId;
    @JsonAnySetter
    private Map<String, Object> any = new HashMap<>();
}
