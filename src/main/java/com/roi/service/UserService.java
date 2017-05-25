package com.roi.service;

import com.roi.model.User;

import java.util.List;

/**
 * Service class for {@link com.roi.model.User}
 *
 * Created by alexander-k on 12.04.17.
 */



public interface UserService {
    /**
     * Add given user to database
     *
     * @param user given user
     */
    void save(User user);

    /**
     * Remove user with given id
     *
     * @param id given id
     */
    void removeUser(Long id);

    /**
     * Get all users from database
     *
     * @return list of all users in database
     */
    List<User> getAllUsers();
    /**
     * Update user in database
     *
     * @param user user with existing id
     * */
    void editUser(User user);
    /**
     * Get all users from database that don't have role admin
     *
     * @return list of users in database without role admin
     */
    List<User> getAllNotAdmins();
    /**
     * Find user with given username
     *
     * @param username given username
     * @return user with given username
     */
    User findByUsername(String username);
    /**
     * Get the highest role of user with given username
     *
     * @param username given username
     * @return highets role of user with given username
     */
    String getRoleByUsername(String username);
}
