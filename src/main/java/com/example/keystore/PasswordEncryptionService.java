package com.example.keystore;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptionService implements EncryptionService{

    public boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt) {
        return false;
    }

    public byte[] getEncryptedPassword(String password, byte[] salt) {
        return new byte[0];
    }

    public byte[] generateSalt() {
        return new byte[0];
    }
}
