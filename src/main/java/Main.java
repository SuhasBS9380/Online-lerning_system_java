package main;

import model.Course;
import model.DiscussionPost;
import model.Assignment;
import model.User;
import service.*;
import storage.FileStorage;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static FileStorage storage = new FileStorage();
    private static UserService userService = new UserService(storage);
    private static CourseService courseService = new CourseService(storage);
    private static AssignmentService assignmentService = new AssignmentService(storage);
    private static DiscussionService discussionService = new DiscussionService(storage);
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;

    public static void main(String[] args) {
        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                switch (currentUser.getRole()) {
                    case "Admin":
                        showAdminMenu();
                        break;
                    case "Instructor":
                        showInstructorMenu();
                        break;
                    case "Student":
                        showStudentMenu();
                        break;
                }
            }
        }
    }

    private static void showLoginMenu() {
        System.out.println("\n=== Online Learning System ===");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            switch (choice) {
                case 1:
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    currentUser = userService.login(username, password);
                    if (currentUser == null) {
                        System.out.println("Invalid credentials!");
                    } else {
                        System.out.println("Logged in as " + currentUser.getRole());
                    }
                    break;
                case 2:
                    System.out.print("Username: ");
                    username = scanner.nextLine();
                    System.out.print("Password: ");
                    password = scanner.nextLine();
                    System.out.print("Role (Admin/Instructor/Student): ");
                    String role = scanner.nextLine();
                    userService.register(username, password, role);
                    System.out.println("Registered successfully!");
                    break;
                case 3:
                    System.exit(0);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void showAdminMenu() {
        System.out.println("\n=== Admin Menu ===");
        System.out.println("1. View All Courses");
        System.out.println("2. Logout");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1:
                    List<Course> courses = courseService.getAllCourses();
                    courses.forEach(c -> System.out.println(c.getId() + ": " + c.getTitle()));
                    break;
                case 2:
                    currentUser = null;
                    break;
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void showInstructorMenu() {
        System.out.println("\n=== Instructor Menu ===");
        System.out.println("1. Create Course");
        System.out.println("2. View My Courses");
        System.out.println("3. Grade Assignments");
        System.out.println("4. View Discussion Forum");
        System.out.println("5. Add Discussion Post");
        System.out.println("6. Logout");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1:
                    System.out.print("Course Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Course Content: ");
                    String content = scanner.nextLine();
                    courseService.createCourse(title, content, currentUser.getId());
                    System.out.println("Course created!");
                    break;
                case 2:
                    List<Course> courses = courseService.getCoursesForInstructor(currentUser.getId());
                    courses.forEach(c -> System.out.println(c.getId() + ": " + c.getTitle()));
                    break;
                case 3:
                    System.out.print("Course ID: ");
                    String courseId = scanner.nextLine();
                    List<Assignment> assignments = assignmentService.getAssignmentsForCourse(courseId);
                    assignments.forEach(a -> System.out.println(a.getId() + ": " + a.getContent() + " (Grade: " + a.getGrade() + ")"));
                    System.out.print("Assignment ID to grade: ");
                    String assignmentId = scanner.nextLine();
                    System.out.print("Grade: ");
                    String grade = scanner.nextLine();
                    assignmentService.gradeAssignment(assignmentId, grade);
                    System.out.println("Assignment graded!");
                    break;
                case 4:
                    System.out.print("Course ID: ");
                    courseId = scanner.nextLine();
                    List<DiscussionPost> posts = discussionService.getPostsForCourse(courseId);
                    posts.forEach(p -> System.out.println(p.getUserId() + ": " + p.getContent()));
                    break;
                case 5:
                    System.out.print("Course ID: ");
                    courseId = scanner.nextLine();
                    System.out.print("Post Content: ");
                    String postContent = scanner.nextLine();
                    discussionService.addPost(courseId, currentUser.getId(), postContent);
                    System.out.println("Post added!");
                    break;
                case 6:
                    currentUser = null;
                    break;
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void showStudentMenu() {
        System.out.println("\n=== Student Menu ===");
        System.out.println("1. View All Courses");
        System.out.println("2. Enroll in Course");
        System.out.println("3. View Enrolled Courses");
        System.out.println("4. Submit Assignment");
        System.out.println("5. View Grades");
        System.out.println("6. View Discussion Forum");
        System.out.println("7. Add Discussion Post");
        System.out.println("8. Logout");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1:
                    List<Course> courses = courseService.getAllCourses();
                    courses.forEach(c -> System.out.println(c.getId() + ": " + c.getTitle()));
                    break;
                case 2:
                    System.out.print("Course ID: ");
                    String courseId = scanner.nextLine();
                    courseService.enrollStudent(courseId, currentUser.getId());
                    System.out.println("Enrolled!");
                    break;
                case 3:
                    List<Course> enrolledCourses = courseService.getEnrolledCourses(currentUser.getId());
                    enrolledCourses.forEach(c -> System.out.println(c.getId() + ": " + c.getTitle() + "\nContent: " + c.getContent()));
                    break;
                case 4:
                    System.out.print("Course ID: ");
                    courseId = scanner.nextLine();
                    System.out.print("Assignment Content: ");
                    String content = scanner.nextLine();
                    assignmentService.submitAssignment(courseId, currentUser.getId(), content);
                    System.out.println("Assignment submitted!");
                    break;
                case 5:
                    System.out.print("Course ID: ");
                    courseId = scanner.nextLine();
                    List<Assignment> assignments = assignmentService.getAssignmentsForCourse(courseId);
                    assignments.stream()
                            .filter(a -> a.getStudentId().equals(currentUser.getId()))
                            .forEach(a -> System.out.println(a.getContent() + ": " + a.getGrade()));
                    break;
                case 6:
                    System.out.print("Course ID: ");
                    courseId = scanner.nextLine();
                    List<DiscussionPost> posts = discussionService.getPostsForCourse(courseId);
                    posts.forEach(p -> System.out.println(p.getUserId() + ": " + p.getContent()));
                    break;
                case 7:
                    System.out.print("Course ID: ");
                    courseId = scanner.nextLine();
                    System.out.print("Post Content: ");
                    String postContent = scanner.nextLine();
                    discussionService.addPost(courseId, currentUser.getId(), postContent);
                    System.out.println("Post added!");
                    break;
                case 8:
                    currentUser = null;
                    break;
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}