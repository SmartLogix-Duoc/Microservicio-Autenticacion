package com.smartlogix.auth.service;

import com.smartlogix.auth.model.User;
import java.util.List;

public interface IUserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
}