package com.group7.fruitswebsite.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dh_comment")
@Getter
@Setter
@JsonIgnoreProperties(value = {"dhProduct", "dhUser"})
public class DhComment extends BaseEntity implements Serializable, Comparable {

    @Column(name = "message", nullable = true)
    private String message;

    @Column(name = "parent_id", nullable = true)
    private Integer parentId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private DhProduct dhProduct;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private DhUser dhUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DhComment dhComment = (DhComment) o;
        return Objects.equals(message, dhComment.message) && Objects.equals(parentId, dhComment.parentId) && Objects.equals(dhProduct, dhComment.dhProduct) && Objects.equals(dhUser, dhComment.dhUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), message, parentId, dhProduct, dhUser);
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof DhComment) {
            DhComment that = (DhComment) o;
            if (this.getParentId() == null) {
                return that.getParentId() == null ? 0 : -1;
            }
            if (that.getParentId() == null) {
                return 1;
            }
            return this.getParentId().compareTo(that.getParentId());
        }
        return 0;
    }
}
