package com.dinesh.springiocdemo.repository;

import com.dinesh.springiocdemo.model.Task;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

@Component
public class TaskRepository {
    private final List<Task> tasks = new ArrayList<>();
    public void addTask(Task task) {
        tasks.add(task);
    }
    public List<Task> findAll() {
        return tasks.stream().
                sorted(Comparator.comparing(this::getPriorityValue))
                .toList();

    }
    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }

    private int getPriorityValue(Task task) {
        return switch (task.getPriority()) {
            case "High" -> 1;
            case "Medium" -> 2;
            case "Low" -> 3;
            default -> 4; // Default for any other priority
        };
    }
    public void updateTaskStatus(int id, String status) {
        tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .ifPresent(task -> task.setStatus(status));
    }

    public Task findById(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void updateTask(Task updatedTask) {
       Task existingTask = findById(updatedTask.getId());
        if (existingTask != null) {
            tasks.remove(existingTask);
            tasks.add(updatedTask);
        }
    }

    public void clearTasks() {
        tasks.clear();
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public List<Task> findByStatus(String status) {
        return tasks.stream()
                .filter(task -> task.getStatus().equalsIgnoreCase(status))
                .toList();
    }

    public List<Task> findByPriority(String priority) {
        return tasks.stream()
                .filter(task -> task.getPriority().equalsIgnoreCase(priority))
                .toList();
    }
    public List<Task> findByName(String name) {
        return tasks.stream()
                .filter(task -> task.getName().equalsIgnoreCase(name))
                .toList();
    }

    public List<Task> findByDescription(String description) {
        return tasks.stream()
                .filter(task -> task.getDescription().equalsIgnoreCase(description))
                .toList();
    }

    public List<Task> findByDueDate(LocalDateTime dueDate) {
        return tasks.stream()
                .filter(task -> task.getDuedate().equals(dueDate))
                .toList();
    }




}
