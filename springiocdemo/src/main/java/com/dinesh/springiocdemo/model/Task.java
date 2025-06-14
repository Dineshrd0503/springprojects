package com.dinesh.springiocdemo.model;

import java.time.LocalDateTime;

public class Task {
    private int id;
    private String name;
    private String description;
    private String status;
    private String priority;
    private LocalDateTime duedate;

    public Task(int id, String name, String description, String status, String priority, LocalDateTime duedate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.duedate = duedate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", priority='" + priority + '\'' +
                ", duedate=" + duedate +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getPriority() {
        return priority;
    }

    public LocalDateTime getDuedate() {
        return duedate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
