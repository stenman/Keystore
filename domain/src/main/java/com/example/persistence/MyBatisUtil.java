package com.example.persistence;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;

@Component
public class MyBatisUtil {

    private static final Logger logger = LoggerFactory.getLogger(MyBatisUtil.class);

    private static SqlSessionFactory factory;

    private String environment;

    public MyBatisUtil(String environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void buildSqlSessionFactory() {
        Reader reader;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        //TODO: Environment is hardcoded, fix this!
        factory = new SqlSessionFactoryBuilder().build(reader, environment);
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return factory;
    }
}
