package com.group7.fruitswebsite.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author duyenthai
 */
@Entity
@Table(name = "dh_subscriber")
@Getter
@Setter
public class DhSubscriber extends BaseEntity {
    private String name;
    private String email;
    private String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DhSubscriber that = (DhSubscriber) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, email, phone);
    }
}
