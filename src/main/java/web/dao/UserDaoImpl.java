package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void addUser(String name, String lastName, int age) {

    }

    @Override
    public void updateUser(User user) { // ??????

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
