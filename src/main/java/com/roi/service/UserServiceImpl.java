package com.roi.service;

import com.roi.dao.RoleDao;
import com.roi.dao.UserDao;
import com.roi.model.Role;
import com.roi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
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
        roles.add(roleDao.getOne(1));
        user.setRoles(roles);
        userDao.save(user);

    }

    @Override
    public void removeUser(Long id) {
        userDao.delete(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public String getRoleByUsername(String username) {
        return userDao.findByUsername(username).getRoles().iterator().next().getName();
    }



    @Override
    @Transactional
    public List<User> getAllUsers(){
            List<User> userList = userDao.findAll();
            return userList;
        }

    @Override
    public void addUser(User user){

    }

    @Override
    public void editUser(User user){
        userDao.saveAndFlush(user);
    }

    @Override
    public User getUserById(Long id){
        return userDao.findOne(id);
    }



}
