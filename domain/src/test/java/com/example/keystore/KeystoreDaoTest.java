package com.example.keystore;

import com.example.domain.User;
import com.example.persistence.KeystoreDao;
import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml", "/test-in-memory-database.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class})
public class KeystoreDaoTest {

    @Autowired
    private KeystoreDao keystoreDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void startDBManager() {
        //For inspecting in-memory DB with HSQL DB manager. Don't forget to suspend breakpoint on Thread rather than All threads
        //org.hsqldb.util.DatabaseManagerSwing.main(new String[]{
        //"--url", "jdbc:hsqldb:mem:testdb", "--noexit"
        //});
    }

    @Test
    @FlywayTest(locationsForMigrate = {"db/migration"})
    public void shouldInsertUserWithUserId6_insertUser() throws SQLException {
        //arrange
        String selectQuery = "SELECT COUNT(*) FROM CREDENTIALS WHERE USER_ID=6";
        User user = new User("test@example.com", "Test", "Testson", "password");

        //act
        int beforeCount = jdbcTemplate.queryForObject(selectQuery, Integer.class);
        keystoreDao.insertUser(user);
        int afterCount = jdbcTemplate.queryForObject(selectQuery, Integer.class);

        //assert
        assertEquals("The database was not prepared properly before this test", 0, beforeCount);
        assertEquals("The record was not deleted properly", 1, afterCount);
    }

    @Test
    @FlywayTest(locationsForMigrate = {"db/migration"})
    public void shouldGetUserWithUserId3_getUserById() throws SQLException {
        //arrange
        int expectedUserId = 3;

        //act
        User user = keystoreDao.getUserById(3);
        int actualUserId = user.getUserId();

        //assert
        assertEquals("User was not fetched correctly from database", expectedUserId, actualUserId);
    }

    @Test
    @FlywayTest(locationsForMigrate = {"db/migration"})
    public void shouldGetAllUsers_getAllUsers() throws SQLException {
        //arrange
        String selectQuery = "SELECT COUNT(*) FROM CREDENTIALS";

        //act
        int numberOfUsers = jdbcTemplate.queryForObject(selectQuery, Integer.class);
        List<User> users = keystoreDao.getAllUsers();

        //assert
        assertEquals("The number of users in the database does not match the fetched number of users", numberOfUsers, users.size());
    }

    @Test
    @FlywayTest(locationsForMigrate = {"db/migration"})
    public void shouldUpdateFirstNameOfUserWithUserId3_updateUser() throws SQLException {
        //arrange
        String selectQuery = "SELECT * FROM CREDENTIALS WHERE USER_ID = ?";
        User user = new User(null, "Newname", null, null);
        user.setUserId(3);

        //act
        keystoreDao.updateUser(user);
        User actualUser = jdbcTemplate.queryForObject(selectQuery, new Object[]{3}, new RowMapper<User>() {
            public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                User user = new User();
                user.setUserId(resultSet.getInt("USER_ID"));
                user.setEmailId(resultSet.getString("EMAIL_ID"));
                user.setFirstName(resultSet.getString("FIRST_NAME"));
                user.setLastName(resultSet.getString("LAST_NAME"));
                user.setPassword(resultSet.getString("PASSWORD"));
                return user;
            }
        });

        //assert
        assertEquals("User was not properly updated", "Newname", actualUser.getFirstName());
    }

    @Test
    @FlywayTest(locationsForMigrate = {"db/migration"})
    public void shouldDeleteUserWithUserId3_deleteUser() throws SQLException {
        //arrange
        String selectQuery = "SELECT COUNT(*) FROM CREDENTIALS WHERE USER_ID=3";

        //act
        int beforeCount = jdbcTemplate.queryForObject(selectQuery, Integer.class);
        keystoreDao.deleteUser(3);
        int afterCount = jdbcTemplate.queryForObject(selectQuery, Integer.class);

        //assert
        assertEquals("The database was not prepared properly before this test", 1, beforeCount);
        assertEquals("The record was not deleted properly", 0, afterCount);
    }
}
