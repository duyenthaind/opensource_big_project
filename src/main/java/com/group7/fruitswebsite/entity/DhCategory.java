package com.group7.fruitswebsite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Entity
@Data
@Table(name = "dh_category")
public class DhCategory extends BaseEntity implements java.io.Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "seo", nullable = false)
    private String seo;

    @Column(name = "parent_id", nullable = true)
    @JsonProperty(value = "parent_id")
    private Integer parentId;

}
