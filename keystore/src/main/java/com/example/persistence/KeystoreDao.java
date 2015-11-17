package com.example.persistence;

import com.example.domain.User;
import com.example.keystore.PasswordEncryptionService;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//TODO: Should this be a @Service?
@Component
public class KeystoreDao {

    private static final Logger logger = LoggerFactory.getLogger(KeystoreDao.class);

    @Autowired
    MyBatisUtil myBatisUtil;

    @Autowired
    PasswordEncryptionService passwordEncryptionService;

    @Autowired
    User user;

    public void insertUser(User user) {
        SqlSession sqlSession = myBatisUtil.getSqlSessionFactory().openSession();
        logger.info("Inserting user: {}", user.toString());
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.insertUser(user);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    public void deleteUser(int userId) {
        SqlSession sqlSession = myBatisUtil.getSqlSessionFactory().openSession();
        logger.info("Deleting user with id: {}", userId);
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.deleteUser(userId);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    public User getUser(int userId) {
        SqlSession sqlSession = myBatisUtil.getSqlSessionFactory().openSession();
        logger.info("Fetching user with id: {}", userId);
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            return userMapper.getUserById(userId);
        } finally {
            sqlSession.close();
        }
    }

    public void updateUser(User user) {
        SqlSession sqlSession = myBatisUtil.getSqlSessionFactory().openSession();
        logger.info("Updating user with id: {}", user.getUserId());
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.updateUser(user);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
