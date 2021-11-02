package com.group7.fruitswebsite.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author duyenthai
 */
@Getter
@Setter
@ToString
public class DhSubscriberModel extends BaseModel{
    private String email;
    private String name;
    private String phone;

    @JsonAnySetter
    private Map<String, Object> any = new HashMap<>();
}
