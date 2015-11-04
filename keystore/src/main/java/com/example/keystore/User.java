package com.example.keystore;

import org.springframework.stereotype.Component;

@Component
public class User {

    private int userId;
    private String emailId;
    private String firstName;
    private String lastName;
    private String password;

    public User() {
    }

    //TODO: Create a user without id will set the id to 0. This is what getUserId will return. Fix!
    public User(String emailId, String firstName, String lastName, String password) {
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
