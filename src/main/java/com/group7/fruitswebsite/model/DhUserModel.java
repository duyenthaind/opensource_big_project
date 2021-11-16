package com.group7.fruitswebsite.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author duyenthai
 */
@Setter
@Getter
public class DhUserModel extends BaseModel{
    private String email;
    private String password;
    private String username;
    @JsonProperty(value = "name")
    @JsonAlias(value = "fullName")
    private String name;
    @JsonIgnore
    private MultipartFile[] files;
    private String avatar;
    private String phone;
    private String address;
}
