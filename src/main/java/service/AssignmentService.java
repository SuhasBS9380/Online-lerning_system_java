package service;

import model.Assignment;
import storage.FileStorage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AssignmentService {
    private FileStorage storage;

    public AssignmentService(FileStorage storage) {
        this.storage = storage;
    }

    public void submitAssignment(String courseId, String studentId, String content) throws IOException {
        Assignment assignment = new Assignment(storage.generateId(), courseId, studentId, content);
        storage.saveAssignment(assignment);
    }

    public List<Assignment> getAssignmentsForCourse(String courseId) throws IOException {
        return storage.loadAssignments().stream()
                .filter(a -> a.getCourseId().equals(courseId))
                .collect(Collectors.toList());
    }

    public void gradeAssignment(String assignmentId, String grade) throws IOException {
        List<Assignment> assignments = storage.loadAssignments();
        for (Assignment assignment : assignments) {
            if (assignment.getId().equals(assignmentId)) {
                assignment.setGrade(grade);
                break;
            }
        }
        storage.writeToFile("data/assignments.txt", assignments);
    }
}