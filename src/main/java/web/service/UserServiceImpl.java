package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createUsersTable() {
        userDao.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    @Override
    public void addUser(String name, String lastName, int age) {
        userDao.addUser(name, lastName, age);
    }

    @Override
    public void updateUser(User user) { // ?????
        userDao.updateUser(user);
    }

    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}
