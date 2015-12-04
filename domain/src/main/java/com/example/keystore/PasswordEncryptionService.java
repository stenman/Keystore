package com.example.keystore;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

@Component
public class PasswordEncryptionService implements EncryptionService {

    public boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt) {
        return false;
    }

    public byte[] getEncryptedPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String algorithm = "PBKDF2WithHmacSHA1";
        int derivedKeyLength = 160;
        int iterations = 20000;

        KeySpec keyspec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);

        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);

        return secretKeyFactory.generateSecret(keyspec).getEncoded();
    }

    public byte[] generateSalt() throws NoSuchAlgorithmException {
        byte[] salt = new byte[8];
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.nextBytes(salt);
        return salt;
    }
}
