package com.roi.service;

public interface SecurityService {

    /**
     * Login user with given username and password automatically
     *
     * @param username given user's username
     * @param password given user's password
     */
    void autoLogin(String username, String password);
}
