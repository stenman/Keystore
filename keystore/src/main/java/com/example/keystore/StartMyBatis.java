package com.example.keystore;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;

import java.io.InputStream;

//TODO: Change name of this class and its methods.
//TODO: Also, this is probably not the way it should be implemented. Fix later.
@Component
public class StartMyBatis {

    public SqlSessionFactory gogo() {
        InputStream inputStream = ClassLoader.class.getResourceAsStream("/mybatis-config.xml");
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
