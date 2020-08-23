package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void addUser(User user) {
        user.addRole(entityManager.find(Role.class, 1L));//Any 'user' will be "ROLE_USER" by default
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public void updateUser(User user) { // ??????
        entityManager.merge(user);
    }

    @Override
    public void removeUserById(long id) {
        User userToDelete = entityManager.find(User.class, id);
        entityManager.remove(userToDelete);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(long id) { return entityManager.find(User.class, id); }
}
