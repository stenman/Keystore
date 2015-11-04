package com.example.keystore;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

//TODO: Change name of this class and its methods.
//TODO: Also, this is probably not the way it should be implemented. Fix later.
@Component
public class MyBatisUtil {

//    public SqlSessionFactory gogo() {
//        InputStream inputStream = ClassLoader.class.getResourceAsStream("/mybatis-config.xml");
//        return new SqlSessionFactoryBuilder().build(inputStream);
//    }

    private static SqlSessionFactory factory;

    @PostConstruct
    public void hej(){
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        InputStream inputStream = ClassLoader.class.getResourceAsStream("/mybatis-config.xml");
        factory = new SqlSessionFactoryBuilder().build(inputStream);
        System.out.println("");
//        Reader reader = null;
//        try {
//            reader = Resources.getResourceAsReader("mybatis-config.xml");
//        } catch (IOException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//        factory = new SqlSessionFactoryBuilder().build(reader);
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return factory;
    }
}
