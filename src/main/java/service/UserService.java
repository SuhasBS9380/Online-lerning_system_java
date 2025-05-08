package service;

import model.User;
import storage.FileStorage;

import java.io.IOException;
import java.util.List;

public class UserService {
    private FileStorage storage;

    public UserService(FileStorage storage) {
        this.storage = storage;
    }

    public User login(String username, String password) throws IOException {
        List<User> users = storage.loadUsers();
        return users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public void register(String username, String password, String role) throws IOException {
        User user = new User(storage.generateId(), username, password, role);
        storage.saveUser(user);
    }
}