package com.example.keystore;

import com.example.keystore.PasswordEncryptionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext-test.xml"})
public class PasswordEncryptionServiceTest {

    @Autowired
    PasswordEncryptionService passwordEncryptionService;

    @Test
    public void generateSaltTest() throws NoSuchAlgorithmException {
        //TODO: Use NATIVEPRNG instance instead?
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[8];
        secureRandom.nextBytes(salt);

        byte[] expectedSalt = passwordEncryptionService.generateSalt();

        assertEquals(expectedSalt, salt);
    }
}
