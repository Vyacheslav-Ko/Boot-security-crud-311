package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void createUsersTable();

    void dropUsersTable();

    void addUser(String name, String lastName, int age);

    void updateUser(User user);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();

}
