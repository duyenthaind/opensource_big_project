package com.group7.fruitswebsite.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author duyenthai
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"files", "pathUploadedAvatar"})
public class DhBlogModel extends BaseModel {
    private String thumbnail;
    private String details;
    private String shortDescription;
    private MultipartFile[] files;
    private String avatar;
}
