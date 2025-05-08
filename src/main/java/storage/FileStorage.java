package storage;

import model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileStorage {
    private static final String USERS_FILE = "data/users.txt";
    private static final String COURSES_FILE = "data/courses.txt";
    private static final String ASSIGNMENTS_FILE = "data/assignments.txt";
    private static final String POSTS_FILE = "data/discussion_posts.txt";

    public FileStorage() {
        new File("data").mkdirs();
    }

    // User Storage
    public void saveUser(User user) throws IOException {
        List<User> users = loadUsers();
        users.add(user);
        writeToFile(USERS_FILE, users);
    }

    public List<User> loadUsers() throws IOException {
        return readFromFile(USERS_FILE, User::fromString);
    }

    // Course Storage
    public void saveCourse(Course course) throws IOException {
        List<Course> courses = loadCourses();
        courses.add(course);
        writeToFile(COURSES_FILE, courses);
    }

    public List<Course> loadCourses() throws IOException {
        return readFromFile(COURSES_FILE, Course::fromString);
    }

    // Assignment Storage
    public void saveAssignment(Assignment assignment) throws IOException {
        List<Assignment> assignments = loadAssignments();
        assignments.add(assignment);
        writeToFile(ASSIGNMENTS_FILE, assignments);
    }

    public List<Assignment> loadAssignments() throws IOException {
        return readFromFile(ASSIGNMENTS_FILE, Assignment::fromString);
    }

    // Discussion Post Storage
    public void saveDiscussionPost(DiscussionPost post) throws IOException {
        List<DiscussionPost> posts = loadDiscussionPosts();
        posts.add(post);
        writeToFile(POSTS_FILE, posts);
    }

    public List<DiscussionPost> loadDiscussionPosts() throws IOException {
        return readFromFile(POSTS_FILE, DiscussionPost::fromString);
    }

    private <T> List<T> readFromFile(String fileName, java.util.function.Function<String, T> mapper) throws IOException {
        List<T> items = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            return items;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    items.add(mapper.apply(line));
                }
            }
        }
        return items;
    }

    public void writeToFile(String fileName, List<?> items) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Object item : items) {
                writer.write(item.toString());
                writer.newLine();
            }
        }
    }

    public String generateId() {
        return UUID.randomUUID().toString();
    }
}