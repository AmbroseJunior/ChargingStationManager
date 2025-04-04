package Service;

import dao.UserDAO;
import vao.ChargingStation;
import vao.User;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService implements Serializable {
    private final UserDAO userDAO = UserDAO.getInstance();

    public void registerUser(String name, String email, double accountBalance, String carType) {
        User user = new User(null, name, email, accountBalance, carType);
        userDAO.insertUser(user);
    }

    public boolean startCharging(String name, ChargingStation station) {
        Optional<User> userOptional = userDAO.getUserByName(name);
        if (userOptional.isEmpty()) {
            System.out.println("User not found");
            return false;
        }
        User user = userOptional.get();
        // Add charging logic if needed
        return true;
    }

    public Optional<User> getUserByName(String name) {
        return userDAO.getUserByName(name);
    }

    public List<User> getAllUsers() {
        List<User> users = userDAO.getAllUsers();
        System.out.println("UserService.getAllUsers()" + users.size() + " users" + users);
        return users;
    }

    public void deleteUser(String name) {
        userDAO.deleteUser(name);
    }

    public void updateUser(User user) {
        userDAO.insertUser(user);
    }

    public void addUser(User user) {
        Optional<User> existingUser = userDAO.getUserByName(user.getName());
        if (existingUser.isPresent()) {
            System.out.println("User with the same name already exists." + user.getName());
        } else {
            userDAO.insertUser(user);
        }
    }
}