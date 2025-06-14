package com.dinesh.springiocdemo.service;

import com.dinesh.springiocdemo.model.Task;
import com.dinesh.springiocdemo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void  createTask(Task taskName) {
        taskRepository.addTask(taskName);
        System.out.println("task created successfully: " + taskName);

    }
    public void deleteTask(int id) {
        taskRepository.deleteTask(id);
        System.out.println("Task with ID " + id + " deleted successfully.");
    }
    public void updateTaskStatus(int id, String status) {
        taskRepository.updateTaskStatus(id, status);
        System.out.println("Task with ID " + id + " updated to status: " + status);
    }
    public Task findById(int id) {
        return taskRepository.findById(id);

    }
    public void updateTask(Task updatedTask) {
        taskRepository.updateTask(updatedTask);
        System.out.println("Task with ID " + updatedTask.getId() + " updated successfully.");
    }
    public void clearTasks() {
        taskRepository.clearTasks();
        System.out.println("All tasks cleared successfully.");
    }
    public void printAllTasks() {
        taskRepository.findAll().forEach(System.out::println);
        System.out.println("Total tasks: " + taskRepository.getTaskCount());
    }
    public void printTaskById(int id) {
        Task task = taskRepository.findById(id);
        if (task != null) {
            System.out.println(task);
        } else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }
    public void printAllTasksSortedByPriority() {
        taskRepository.findAll().forEach(System.out::println);
        System.out.println("all tsks printed successfully.");

    }
    public void printAllTasksSortedByDueDate() {
        taskRepository.findAll().stream()
                .sorted((t1, t2) -> t1.getDuedate().compareTo(t2.getDuedate()))
                .forEach(System.out::println);
        System.out.println("All tasks sorted by due date printed successfully.");
    }
    public void printAllTasksSortedByStatus() {
        taskRepository.findAll().stream()
                .sorted((t1, t2) -> t1.getStatus().compareTo(t2.getStatus()))
                .forEach(System.out::println);
        System.out.println("All tasks sorted by status printed successfully.");

    }
    public void printAllTasksSortedByName() {
        taskRepository.findAll().stream()
                .sorted((t1, t2) -> t1.getName().compareTo(t2.getName()))
                .forEach(System.out::println);
        System.out.println("All tasks sorted by name printed successfully.");
    }
    public void printAllTasksSortedByDescription() {
        taskRepository.findAll().stream()
                .sorted((t1, t2) -> t1.getDescription().compareTo(t2.getDescription()))
                .forEach(System.out::println);
        System.out.println("All tasks sorted by description printed successfully.");
    }
    public void printAllTasksSortedById() {
        taskRepository.findAll().stream()
                .sorted((t1, t2) -> Integer.compare(t1.getId(), t2.getId()))
                .forEach(System.out::println);
        System.out.println("All tasks sorted by ID printed successfully.");
    }
    public int getTaskCount() {
        return taskRepository.getTaskCount();
    }
    public void printTasksByStatus(String status) {
        taskRepository.findByStatus(status).forEach(System.out::println);
    }
    public void printTasksByPriority(String priority) {
        taskRepository.findByPriority(priority).forEach(System.out::println);
    }
    public void printTasksByName(String name) {
        taskRepository.findByName(name).forEach(System.out::println);
    }
    public void printTasksByDescription(String description) {
        taskRepository.findByDescription(description).forEach(System.out::println);
    }
    public void printTasksByDueDate(LocalDateTime dueDate) {
        taskRepository.findByDueDate(dueDate).forEach(System.out::println);
    }

    public void printTaskCount() {
        System.out.println("Total tasks: " + taskRepository.getTaskCount());
    }






}
