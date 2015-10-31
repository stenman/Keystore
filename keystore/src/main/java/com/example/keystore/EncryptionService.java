package com.example.keystore;

import java.security.NoSuchAlgorithmException;

public interface EncryptionService {
    boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt);

    byte[] getEncryptedPassword(String password, byte[] salt);

    byte[] generateSalt() throws NoSuchAlgorithmException;
}
