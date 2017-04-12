package com.roi.service;

import com.roi.model.User;

/**
 * Service class for {@link com.roi.model.User}
 *
 * Created by alexander-k on 12.04.17.
 */



public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
