package model;

public class DiscussionPost {
    private String id;
    private String courseId;
    private String userId;
    private String content;

    public DiscussionPost(String id, String courseId, String userId, String content) {
        this.id = id;
        this.courseId = courseId;
        this.userId = userId;
        this.content = content;
    }

    // Getters
    public String getId() { return id; }
    public String getCourseId() { return courseId; }
    public String getUserId() { return userId; }
    public String getContent() { return content; }

    @Override
    public String toString() {
        return id + "," + courseId + "," + userId + "," + content;
    }

    public static DiscussionPost fromString(String line) {
        String[] parts = line.split(",", 4);
        return new DiscussionPost(parts[0], parts[1], parts[2], parts[3]);
    }
}