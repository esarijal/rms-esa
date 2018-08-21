package com.mitrais.rms.service;

import javax.security.auth.login.LoginException;

public interface LoginService {
    boolean validateUser(String user, String password) throws LoginException;
}
