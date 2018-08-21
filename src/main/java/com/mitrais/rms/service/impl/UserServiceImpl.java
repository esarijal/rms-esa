package com.mitrais.rms.service.impl;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;
import com.mitrais.rms.service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserDao userDao = UserDaoImpl.getInstance();

    private UserService userService = UserServiceImpl.getInstance();

    private UserServiceImpl(){}

    @Override
    public Optional<User> findUser(String userid) {
        try {
            return userDao.find(userid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<User> listUser() {
        try {
            return userDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean deleteUser(String userId) {
        try {
            User user = userDao.find(userId).orElse(null);
            return userDao.delete(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        try {
            // TODO hash password
            return userDao.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addUser(User user) {
        try {
            // TODO hash password
            return userDao.save(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static class SingletonHelper
    {
        private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    }

    public static UserService getInstance(){
        return SingletonHelper.INSTANCE;
    }


}
