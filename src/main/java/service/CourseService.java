package service;

import model.Course;
import model.User;
import storage.FileStorage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CourseService {
    private FileStorage storage;

    public CourseService(FileStorage storage) {
        this.storage = storage;
    }

    public void createCourse(String title, String content, String instructorId) throws IOException {
        Course course = new Course(storage.generateId(), title, instructorId, content);
        storage.saveCourse(course);
    }

    public List<Course> getAllCourses() throws IOException {
        return storage.loadCourses();
    }

    public List<Course> getCoursesForInstructor(String instructorId) throws IOException {
        return storage.loadCourses().stream()
                .filter(c -> c.getInstructorId().equals(instructorId))
                .collect(Collectors.toList());
    }

    public List<Course> getEnrolledCourses(String studentId) throws IOException {
        return storage.loadCourses().stream()
                .filter(c -> c.getEnrolledStudentIds().contains(studentId))
                .collect(Collectors.toList());
    }

    public void enrollStudent(String courseId, String studentId) throws IOException {
        List<Course> courses = storage.loadCourses();
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                course.addStudent(studentId);
                break;
            }
        }
        storage.writeToFile("data/courses.txt", courses);
    }
}