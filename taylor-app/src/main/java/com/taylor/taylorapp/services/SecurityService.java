package com.taylor.taylorapp.services;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
