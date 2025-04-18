package Service;

import dao.UserDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import vao.User;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class UserService  implements Serializable {
    @Inject
    private UserDAO userDAO;

    public User registerUser(User user) {
        return userDAO.create(user);
    }

    public Optional<User> getUserByName(String name) {
        return userDAO.findByName(name);
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public void deleteUser(String name) {
        userDAO.delete(name);
    }

    public User updateUser(User user) {
        return userDAO.update(user);
    }
}