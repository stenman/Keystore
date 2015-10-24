package com.example.keystore;

import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public class PasswordEncryptionService implements EncryptionService {

    public boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt) {
        return false;
    }

    public byte[] getEncryptedPassword(String password, byte[] salt) {
        return new byte[0];
    }

    public byte[] generateSalt() throws NoSuchAlgorithmException {
        byte[] salt = new byte[8];
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.nextBytes(salt);
        return salt;
    }
}
