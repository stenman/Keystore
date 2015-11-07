package com.example.keystore;

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
        KeystoreService keystoreService = (KeystoreService) applicationContext.getBean("keystoreService");

//        User user = new User("hej@hej.com", "Olle", "Pall", "ollepall");
//        keystoreService.insertUser(user);
    }

}
