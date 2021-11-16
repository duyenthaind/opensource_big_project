package com.group7.fruitswebsite.service;

import com.group7.fruitswebsite.entity.DhRole;

import java.util.Optional;

/**
 * @author duyenthai
 */
public interface RoleService {
    Optional<DhRole> findById(Integer id);

    Optional<DhRole> getClientRole();
}
