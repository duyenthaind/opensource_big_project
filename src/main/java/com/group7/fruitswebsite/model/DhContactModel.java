package com.group7.fruitswebsite.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author duyenthai
 */
@Setter
@Getter
@ToString
public class DhContactModel extends BaseModel{
    @JsonProperty(value = "contact_email")
    @JsonAlias(value = "email")
    private String email;
    @JsonProperty(value = "contact_name")
    @JsonAlias(value = "name")
    private String name;
    @JsonProperty(value = "contact_message")
    @JsonAlias(value = "message")
    private String message;
}
