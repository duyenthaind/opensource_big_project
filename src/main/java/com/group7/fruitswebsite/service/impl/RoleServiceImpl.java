package com.group7.fruitswebsite.service.impl;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.entity.DhRole;
import com.group7.fruitswebsite.repository.RoleRepository;
import com.group7.fruitswebsite.service.RoleService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author duyenthai
 */
@Service
@Log4j
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Override
    public Optional<DhRole> findById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<DhRole> getClientRole() {
        try {
            return roleRepository.findByName(Constants.RoleName.USER.getName());
        } catch (Exception ex) {
            log.error("Error get client role, ", ex);
        }
        return Optional.empty();
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
