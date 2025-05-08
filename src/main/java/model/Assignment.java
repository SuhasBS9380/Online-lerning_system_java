package model;

public class Assignment {
    private String id;
    private String courseId;
    private String studentId;
    private String content;
    private String grade;

    public Assignment(String id, String courseId, String studentId, String content) {
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
        this.content = content;
        this.grade = "Not graded";
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getCourseId() { return courseId; }
    public String getStudentId() { return studentId; }
    public String getContent() { return content; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return id + "," + courseId + "," + studentId + "," + content + "," + grade;
    }

    public static Assignment fromString(String line) {
        String[] parts = line.split(",", 5);
        Assignment assignment = new Assignment(parts[0], parts[1], parts[2], parts[3]);
        if (parts.length > 4) {
            assignment.setGrade(parts[4]);
        }
        return assignment;
    }
}