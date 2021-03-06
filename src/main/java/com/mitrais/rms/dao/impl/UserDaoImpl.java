package com.mitrais.rms.dao.impl;

import com.mitrais.rms.dao.DataSourceFactory;
import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private UserDaoImpl(){}

    private static class SingletonHelper
    {
        private static final UserDaoImpl INSTANCE = new UserDaoImpl();
    }

    public static UserDao getInstance(){
        return SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<User> find(String userId) throws SQLException {
        try (Connection conn = DataSourceFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM users WHERE userid = ?");
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getString("userid"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getString("email")
                );
                user.setPassword(rs.getString("password"));
                return Optional.of(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return Optional.empty();
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection conn = DataSourceFactory.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User user = new User(
                        rs.getString("userid"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getString("email")
                );
                user.setPassword(rs.getString("password"));
                users.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return users;
    }

    @Override
    public boolean save(User user) throws SQLException {
        try (Connection conn = DataSourceFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO users(userid, firstname, lastname, dob, email, password) VALUES " +
                            "(?, ?, ?, ?, ?, ?)");
            stmt.setString(1, user.getUserId());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setDate(4, Date.valueOf(user.getDob()));
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getPassword());
            int i = stmt.executeUpdate();
            if(i == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return false;
    }

    @Override
    public boolean update(User user) throws SQLException {
        try (Connection conn = DataSourceFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE users set " +
                            "firstname = ?, " +
                            "lastname = ?, " +
                            "dob = ?, " +
                            "email = ?, " +
                            "password= ? " +
                            "WHERE userid = ?");
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setDate(3, Date.valueOf(user.getDob()));
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());
            stmt.setString(6, user.getUserId());
            int i = stmt.executeUpdate();
            if(i == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return false;
    }

    @Override
    public boolean delete(User user) throws SQLException {
        try (Connection conn = DataSourceFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM users WHERE userid = ?");
            stmt.setString(1, user.getUserId());
            int i = stmt.executeUpdate();
            if(i == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return false;
    }
}
