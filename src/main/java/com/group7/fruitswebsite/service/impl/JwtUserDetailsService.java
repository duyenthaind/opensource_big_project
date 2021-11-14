package com.group7.fruitswebsite.service.impl;

import com.group7.fruitswebsite.entity.DhUser;
import com.group7.fruitswebsite.repository.UserRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author duyenthai
 */
@Service
@Log4j
public class JwtUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<DhUser> optional = userRepository.findByUsername(username);
        if (!optional.isPresent()) {
            return null;
        }
        DhUser user = optional.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getDhRoles().forEach(val -> authorities.add(new SimpleGrantedAuthority(val.getName())));
        return new User(username, user.getPassword(), authorities);
    }

    @Autowired
    public void setUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
