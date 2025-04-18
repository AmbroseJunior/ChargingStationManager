package dao;

import vao.User;

import java.util.List;

public interface UserDAOLocal {
    void save(User user);
    User findByName(String name);
    List<User> findAll();
    void update(User user);
    void delete(String name);
}
