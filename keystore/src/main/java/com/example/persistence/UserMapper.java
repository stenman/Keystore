package com.example.persistence;

import com.example.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    public void insertUser(User user);

    public User getUserById(Integer userId);

    public List<User> getAllUsers();

    public void updateUser(User user);

    public void deleteUser(Integer userId);
}