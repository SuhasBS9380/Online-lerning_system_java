package model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String id;
    private String title;
    private String instructorId;
    private String content;
    private List<String> enrolledStudentIds;

    public Course(String id, String title, String instructorId, String content) {
        this.id = id;
        this.title = title;
        this.instructorId = instructorId;
        this.content = content;
        this.enrolledStudentIds = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getInstructorId() { return instructorId; }
    public String getContent() { return content; }
    public List<String> getEnrolledStudentIds() { return enrolledStudentIds; }
    public void addStudent(String studentId) { enrolledStudentIds.add(studentId); }

    @Override
    public String toString() {
        return id + "," + title + "," + instructorId + "," + content + "," + String.join(";", enrolledStudentIds);
    }

    public static Course fromString(String line) {
        String[] parts = line.split(",", 4);
        Course course = new Course(parts[0], parts[1], parts[2], parts[3]);
        if (parts.length > 4 && !parts[4].isEmpty()) {
            for (String studentId : parts[4].split(";")) {
                course.addStudent(studentId);
            }
        }
        return course;
    }
}