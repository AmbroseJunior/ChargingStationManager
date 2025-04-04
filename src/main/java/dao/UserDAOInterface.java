package dao;
import vao.User;

import java.util.List;
import java.util.Optional;

public interface UserDAOInterface {
    void insertUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserByName(String name);
    void updateUser(String email, String name);
    void deleteUser(String name);
}
