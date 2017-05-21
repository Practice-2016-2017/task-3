package com.roi.service;

import com.roi.dao.UserDao;
import com.roi.model.Role;
import com.roi.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link org.springframework.security.core.userdetails.UserDetailsService} interface.
 *
 * Created by alexander-k on 12.04.17.
 */

public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = Logger.getLogger(UserDetailsService.class);

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional( readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user by username "+username);
        User user = userDao.findByUsername(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
