package com.example.keystore;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
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

    @Test
    public void shouldGenerateAnEncryptedPassword_getEncryptedPassword() {
        //arrange
        byte[] salt = new byte[8];

        //act
        byte[] actualKey = new byte[0];
        try {
            actualKey = passwordEncryptionService.getEncryptedPassword("password", salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        byte[] expectedKey = null;
        try {
            expectedKey = getEncryptedPassword("password", salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        String expected = Hex.encodeHexString(expectedKey);
        String actual = Hex.encodeHexString(actualKey);

        //assert
        assertEquals(actual, expected);
    }

    private byte[] getEncryptedPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String algorithm = "PBKDF2WithHmacSHA1";
        int derivedKeyLength = 160;
        int iterations = 20000;

        KeySpec keyspec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);

        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);

        return secretKeyFactory.generateSecret(keyspec).getEncoded();
    }
}
