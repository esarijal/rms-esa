package com.mitrais.rms.dao;

import com.mitrais.rms.model.User;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Provides interface specific to {@link User} data
 */
public interface UserDao extends Dao<User, Long>
{
    /**
     * Find {@link User} by its username
     * @param firstName username
     * @return user
     */
    Optional<User> findByFirstName(String firstName) throws SQLException;
}
