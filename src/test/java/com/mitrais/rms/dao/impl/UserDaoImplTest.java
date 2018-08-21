package com.mitrais.rms.dao.impl;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    private UserDao userDao = UserDaoImpl.getInstance();

    @Test
    public void userTest(){
        try {
            List<User> users = userDao.findAll();
            assertEquals(0, users.size());

            User user = new User(2l, "Esa", "Rijal",
                    LocalDate.of(1986, 10,21), "esa.rijal@gmail.com");
            userDao.save(user);

            users = userDao.findAll();
            assertEquals(1, users.size());

            User withFirstName = userDao.findByFirstName("Esa").orElse(null);
            assertNotNull(withFirstName);
            assert withFirstName!=null;
            assertEquals("esa.rijal@gmail.com", withFirstName.getEmail());

            withFirstName.setEmail("esarijal.mustaqbal@mitrais.com");
            userDao.update(withFirstName);
            User updatedEmail = userDao.find(withFirstName.getUserId()).orElse(null);
            assert updatedEmail!=null;
            assertNotEquals("esa.rijal@gmail.com", updatedEmail.getEmail());
            assertEquals("esarijal.mustaqbal@mitrais.com", updatedEmail.getEmail());


        } catch (SQLException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Before
    public void setUp() throws Exception {
        deleteAll();
    }

    @After
    public void tearDown() throws Exception {
        deleteAll();
    }

    private void deleteAll() throws SQLException {
        List<User> all = userDao.findAll();
        all.stream().filter(user -> user.getUserId() != null).forEach(u -> {
            try {
                userDao.delete(u);
            } catch (SQLException e) {
                e.printStackTrace();
                fail(e.getMessage());
            }
        });
    }
}