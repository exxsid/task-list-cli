package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Task {

    private int id;
    private String description;
    private String status;

    private String createdAt;

    private String updatedAt;

    Task() {}

    public Task(int id, String description, String status, String createdTime, String updatedTime) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdTime;
        this.updatedAt = updatedTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
