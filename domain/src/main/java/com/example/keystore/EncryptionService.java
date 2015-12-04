package com.example.keystore;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface EncryptionService {
    boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt);

    byte[] getEncryptedPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException;

    byte[] generateSalt() throws NoSuchAlgorithmException;
}
