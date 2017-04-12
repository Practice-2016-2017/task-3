package com.roi.service;

/**
 * Service for security
 *
 * Created by alexander-k on 12.04.17.
 */
public interface SecurityService {

    String findLoggedInUsername();
    void autoLogin(String username, String password);
}
