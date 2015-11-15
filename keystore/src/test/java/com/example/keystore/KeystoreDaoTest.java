package com.example.keystore;

import com.example.persistence.KeystoreDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml", "/test-in-memory-database.xml"})
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

    @Before
    public void setup() {
    }

    @Test
    public void shouldInsertUser_insertUser() {
        //arrange
        //act
        //assert
    }

    //TODO: The database is not properly resetted between tests, fix!
    //TODO: Either TRUNCATE/DROP tables and build them up anew
    //TODO: ... or Rollback in a @Transaction

//    @Test
//    public void shouldDeleteUser_deleteUser() throws SQLException {
//        //arrange
//        String selectQuery = "SELECT COUNT(*) FROM CREDENTIALS WHERE USER_ID=1";
//
//        //act
//        int beforeCount = jdbcTemplate.queryForObject(selectQuery, Integer.class);
//        keystoreDao.deleteUser(1);
//        int afterCount = jdbcTemplate.queryForObject(selectQuery, Integer.class);
//
//        //assert
//        assertEquals("The database was not prepared properly before this test", 1, beforeCount);
//        assertEquals("The record was not deleted properly", 0, afterCount);
//    }
//
//    @Test
//    public void shouldDeleteUser2_deleteUser() throws SQLException {
//        //arrange
//        String selectQuery = "SELECT COUNT(*) FROM CREDENTIALS WHERE USER_ID=1";
//
//        //act
//        int beforeCount = jdbcTemplate.queryForObject(selectQuery, Integer.class);
//        keystoreDao.deleteUser(1);
//        int afterCount = jdbcTemplate.queryForObject(selectQuery, Integer.class);
//
//        //assert
//        assertEquals("The database was not prepared properly before this test", 1, beforeCount);
//        assertEquals("The record was not deleted properly", 0, afterCount);
//    }

    @After
    public void tearDown() {
    }
}
