package com.crio.xlido.entities;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question {
    private final Long id;
    private final String content;
    private final Long userId;
    private final Long eventId;
    private final Set<Long> upvotedUsers; // Set to store user IDs who upvoted the question
    private List<Reply> replies;
    private final LocalDateTime createdAt;

    public Question(Long id, String content, Long userId, Long eventId) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.eventId = eventId;
        this.upvotedUsers = new HashSet<>();
        this.replies = new ArrayList<>();
        this.createdAt = LocalDateTime.now(); // Set creation time
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public Set<Long> getUpvotedUsers() {
        return upvotedUsers;
    }

    public void addUpvote(Long userId) {
        upvotedUsers.add(userId);
    }

    public boolean hasUpvoted(Long userId) {
        return upvotedUsers.contains(userId);
    }

     // Method to get the vote count
     public int getVoteCount() {
        return upvotedUsers.size();
    }


    public List<Reply> getReplies() {
        return replies;
    }

    // Method to add a reply to the question
    public void addReply(String content, Long userId) {
        replies.add(new Reply(content, userId));
    }

     // Getter for createdAt
     public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", content=" + content + ", userId=" + userId + ", eventId=" + eventId 
                + ", voteCount=" + getVoteCount() + ", replies=" + replies + "]";
    }
    // Equals, hashCode, and toString methods can be added as needed

     // Inner Reply class to store reply information
     public static class Reply {
        private String content;
        private Long userId;

        public Reply(String content, Long userId) {
            this.content = content;
            this.userId = userId;
        }

        // Getters
        public String getContent() {
            return content;
        }

        public Long getUserId() {
            return userId;
        }

        @Override
        public String toString() {
            return "- User " + userId + ": " + content;
        }
    }
    
}
