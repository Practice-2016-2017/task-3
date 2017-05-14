package com.roi.service;

import com.roi.model.Hotel;
import com.roi.model.User;

import java.util.List;

/**
 * Service class for {@link com.roi.model.User}
 *
 * Created by alexander-k on 12.04.17.
 */



public interface UserService {
    void save(User user);
    void removeUser(Long id);
    List<User> getAllUsers();
    void addUser(User user);
    void editUser(User user);
    User getUserById(Long id);

    User findByUsername(String username);
}
