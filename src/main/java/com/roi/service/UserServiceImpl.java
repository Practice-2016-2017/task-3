package com.roi.service;

import com.roi.dao.RoleDao;
import com.roi.dao.UserDao;
import com.roi.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import com.roi.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Implementation of {@link UserService}
 *
 * Created by alexander-k on 12.04.17.
 */

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1L));
        user.setRoles(roles);
        userDao.save(user);

    }

    @Override
    public User findByUsername(String username) {

        return userDao.findByUsername(username);

    }
}
