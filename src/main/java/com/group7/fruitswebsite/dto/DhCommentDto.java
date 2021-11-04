package com.group7.fruitswebsite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author duyenthai
 */
@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DhCommentDto extends BaseDto {
    private String message;
    private String displayName;
    private Integer productId;
    private Integer parentId;
    private List<DhCommentDto> childComments = new CopyOnWriteArrayList<>();
}
