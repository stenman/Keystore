package com.example.persistence;

import com.example.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    void insertUser(User user);

    User getUserById(Integer userId);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(Integer userId);
}
