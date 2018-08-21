package com.mitrais.rms.service;

import com.mitrais.rms.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface UserService {
    Optional<User> findUser(String userid);
    List<User> listUser();
    boolean deleteUser(String userId);
    boolean updateUser(User user);
    boolean addUser(User user);
}
