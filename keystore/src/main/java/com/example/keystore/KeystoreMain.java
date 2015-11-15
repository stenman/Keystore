package com.example.keystore;

import com.example.domain.User;
import com.example.persistence.KeystoreDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * A class meant for laborating during development, remove when Service is implemented
 */
//TODO: Remove this class when tests and Service is in place
public class KeystoreMain {

    final static Logger logger = LoggerFactory.getLogger(KeystoreMain.class);

    public static void main(String[] args) {
        logger.info("Initializing Spring context.");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
        KeystoreDao keystoreDao = (KeystoreDao) applicationContext.getBean("keystoreDao");

//        User user = new User("hej@hej.com", "Olle", "Pall", "ollepall");
//        keystoreService.insertUser(user);

//        User user = keystoreDao.getUser(1);
//        System.out.println(user.toString());
    }

}
