package model;

public class User {
    private String id;
    private String username;
    private String password;
    private String role; // Admin, Instructor, Student

    public User(String id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    @Override
    public String toString() {
        return id + "," + username + "," + password + "," + role;
    }

    public static User fromString(String line) {
        String[] parts = line.split(",");
        return new User(parts[0], parts[1], parts[2], parts[3]);
    }
}