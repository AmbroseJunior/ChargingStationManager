package dao;

import vao.User;

import java.util.*;

public class UserDAO implements UserDAOInterface {
    private static UserDAO instance;
    private static final Map<String, User> users = new HashMap<>();

    private UserDAO() {}

    public static UserDAO getInstance() {
        if (instance == null) {
            synchronized (UserDAO.class) {
                if (instance == null) {
                    instance = new UserDAO();
                }
            }
        }
        return instance;
    }

    public User getUser(String name) {
        return users.get(name);
    }

    public static void saveUser(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public void insertUser(User user) {
        if (user != null && user.getId() != null) {
            users.put(user.getName(), user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> getUserByName(String name) {
        User user = users.get(name);
        return Optional.ofNullable(user);
    }

    @Override
    public void updateUser(String email, String name) {
        Optional<User> userOpt = getUserByName(name);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setEmail(email);
            users.put(name, user);
        }
    }

    @Override
    public void deleteUser(String name) {
        users.remove(name);
    }
}