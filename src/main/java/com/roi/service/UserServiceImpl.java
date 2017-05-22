package com.roi.service;

import com.roi.dao.RoleDao;
import com.roi.dao.UserDao;
import com.roi.model.Role;
import com.roi.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of {@link UserService}
 */

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);


    private final UserDao userDao;

    private final RoleDao roleDao;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public void save(User user) {
        log.info("Saving user " + user.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1));
        user.setRoles(roles);
        userDao.save(user);

    }

    @Override
    public void removeUser(Long id) {
        log.info("Removing user " + id);
        userDao.delete(id);
    }

    @Override
    public User findByUsername(String username) {
        log.info("Finding user by username " + username);
        return userDao.findByUsername(username);
    }

    @Override
    public String getRoleByUsername(String username) {
        log.info("Getting role by username " + username);
        User user = userDao.findByUsername(username);
        if (hasRole(user.getRoles(), "ROLE_ADMIN")) {
            return "ROLE_ADMIN";
        } else if (hasRole(user.getRoles(), "ROLE_MANAGER")) {
            return "ROLE_MANAGER";
        }
        return "ROLE_USER";

    }


    @Override
    @Transactional
    public List<User> getAllUsers() {
        log.info("Getting list of all users");
        return userDao.findAll();
    }

    @Override
    @Transactional
    public List<User> getAllNotAdmins() {
        log.info("Getting list of all users without role ROLE_ADMIN");
        List<User> userList = userDao.findAll();
        userList.removeIf(user -> hasRole(user.getRoles(), "ROLE_ADMIN"));
        return userList;
    }


    @Override
    public void editUser(User user) {
        userDao.saveAndFlush(user);
    }


    private boolean hasRole(Set<Role> roles, String toCheck) {
        for (Role role : roles) {
            if (role.getName().equals(toCheck)) {
                return true;
            }
        }
        return false;
    }

}
