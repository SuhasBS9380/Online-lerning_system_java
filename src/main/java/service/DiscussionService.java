package service;

import model.DiscussionPost;
import storage.FileStorage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DiscussionService {
    private FileStorage storage;

    public DiscussionService(FileStorage storage) {
        this.storage = storage;
    }

    public void addPost(String courseId, String userId, String content) throws IOException {
        DiscussionPost post = new DiscussionPost(storage.generateId(), courseId, userId, content);
        storage.saveDiscussionPost(post);
    }

    public List<DiscussionPost> getPostsForCourse(String courseId) throws IOException {
        return storage.loadDiscussionPosts().stream()
                .filter(p -> p.getCourseId().equals(courseId))
                .collect(Collectors.toList());
    }
}