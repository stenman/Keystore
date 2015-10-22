package com.example.keystore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KeystoreMain {

    final static Logger logger = LoggerFactory.getLogger(KeystoreMain.class);

    public static void main(String[] args) {
        logger.info("Initializing Spring context.");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");
        EncryptionService passwordEncryptionService = (EncryptionService) applicationContext.getBean("passwordEncryptionService");
    }

}
