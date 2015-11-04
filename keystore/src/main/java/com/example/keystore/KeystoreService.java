package com.example.keystore;

import com.example.mappers.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//TODO: Change name of this class and its methods.
//TODO: Also, this is probably not the way it should be implemented. Fix later.
@Component
public class KeystoreService {
    @Autowired
    MyBatisUtil myBatisUtil;

    @Autowired
    PasswordEncryptionService passwordEncryptionService;

    @Autowired
    User user;

    public void startService() {
        System.out.println("It works!");
    }

    public void insertUser(User user) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.insertUser(user);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
