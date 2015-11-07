package com.example.keystore;

import com.example.mappers.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//TODO: Should this be a @Service?
@Component
public class KeystoreService {

    private static final Logger logger = LoggerFactory.getLogger(KeystoreService.class);

    @Autowired
    MyBatisUtil myBatisUtil;

    @Autowired
    PasswordEncryptionService passwordEncryptionService;

    @Autowired
    User user;

    public void insertUser(User user) {
        SqlSession sqlSession = myBatisUtil.getSqlSessionFactory().openSession();
        logger.info("Inserting user {}", user.toString());
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.insertUser(user);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
