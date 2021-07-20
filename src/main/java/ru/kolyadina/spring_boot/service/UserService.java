package ru.kolyadina.spring_boot.service;

import ru.kolyadina.spring_boot.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void addUser(User user);
    User getUserById(long id);
    void deleteUser(long id);
    void updateUser(User user);
    User getUserByLogin(String userLogin);
}
