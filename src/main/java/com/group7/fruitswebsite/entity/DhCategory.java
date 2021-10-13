package com.group7.fruitswebsite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Objects;

@Entity
@Setter
@Getter
@ToString
@Table(name = "dh_category")
public class DhCategory extends BaseEntity implements java.io.Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "seo", nullable = false)
    private String seo;
    
    @Column(name = "avatar", nullable = false)
    private String avatar;

    @Column(name = "parent_id", nullable = true)
    @JsonProperty(value = "parent_id")
    private Integer parentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DhCategory category = (DhCategory) o;
        return Objects.equals(name, category.name) && Objects.equals(description, category.description)
        		&& Objects.equals(seo, category.seo) 
        		&& Objects.equals(parentId, category.parentId)
        		&& Objects.equals(avatar, category.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, seo,avatar, parentId);
    }
}
