package com.mitrais.rms.dao;

import com.mitrais.rms.model.User;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Provides interface specific to {@link User} data
 */
public interface UserDao extends Dao<User, String>
{

}
