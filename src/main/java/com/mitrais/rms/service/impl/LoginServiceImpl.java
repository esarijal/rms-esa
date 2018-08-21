package com.mitrais.rms.service.impl;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;
import com.mitrais.rms.service.LoginService;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.Optional;

public class LoginServiceImpl implements LoginService {

    private UserDao userDao = UserDaoImpl.getInstance();

    private LoginServiceImpl(){}

    private static class SingletonHelper
    {
        private static final LoginServiceImpl INSTANCE = new LoginServiceImpl();
    }

    public static LoginService getInstance(){
        return SingletonHelper.INSTANCE;
    }

    @Override
    public boolean validateUser(String user, String password) throws LoginException {
        try {
            User loggingIn = userDao.find(user).orElse(null);
            if(loggingIn == null){
                throw new LoginException("User not found");
            }

            return password.equals(loggingIn.getPassword()); // TODO hash password

        } catch (SQLException e) {
            System.err.println("User " + user + " not found");
            e.printStackTrace();
            throw new LoginException("User not found");
        }
    }


}
