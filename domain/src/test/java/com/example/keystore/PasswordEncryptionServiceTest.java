package com.example.keystore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
public class PasswordEncryptionServiceTest {

    @Autowired
    PasswordEncryptionService passwordEncryptionService;

    @Test
    public void saltShouldBeCorrectLength_generateSaltTest() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[8];
        secureRandom.nextBytes(salt);

        String expectedSalt = Base64.getEncoder().encodeToString(salt);
        byte[] testTargetSalt = passwordEncryptionService.generateSalt();
        String actualSalt = Base64.getEncoder().encodeToString(testTargetSalt);

        assertEquals(expectedSalt.length(), actualSalt.length());
    }

    @Test
    public void saltShouldEndWithEquals_generateSaltTest() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[8];
        secureRandom.nextBytes(salt);

        byte[] testTargetSalt = passwordEncryptionService.generateSalt();
        String actualSalt = Base64.getEncoder().encodeToString(testTargetSalt);

        assertEquals("=", actualSalt.substring(11, 12));
    }
}
