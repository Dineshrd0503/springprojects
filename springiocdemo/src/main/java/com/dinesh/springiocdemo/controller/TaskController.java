package com.dinesh.springiocdemo.controller;

import com.dinesh.springiocdemo.model.Task;
import com.dinesh.springiocdemo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScanner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

@Component
public class TaskController {
    @Autowired
    private TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    public void start(){
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("welcomre to task manager");
            System.out.println("1. Create Task");
            System.out.println("2. Delete Task");
            System.out.println("3. Update Task Status");
            System.out.println("4. Find Task by ID");
            System.out.println("5. Update Task");
            System.out.println("6. Clear All Tasks");
            System.out.println("7. Print All Tasks");
            System.out.println("8. Print All Tasks Sorted by Priority");
            System.out.println("9. Print All Tasks Sorted by Due Date");
            System.out.println("10. Print All Tasks Sorted by Status");
            System.out.println("11. Print All Tasks Sorted by Name");
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline character
            switch (choice) {
                case 1:
                        System.out.print("Enter task ID: ");
                        int id = sc.nextInt();
                        sc.nextLine(); // Consume newline
                        System.out.print("Enter task name: ");
                        String taskName = sc.nextLine();
                        System.out.print("Enter task description: ");
                        String description = sc.nextLine();
                        System.out.print("Enter task status: ");
                        String status = sc.nextLine();
                        System.out.print("Enter task priority (High/Medium/Low): ");
                        String priority = sc.nextLine();
                       System.out.print("Enter due date (YYYY-MM-DD): ");
                    String dueDateStr = sc.nextLine();
                    LocalDateTime dueDate;
                    try {
                        LocalDate localDate = LocalDate.parse(dueDateStr);
                        dueDate = localDate.atStartOfDay();
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format! Use YYYY-MM-DD (e.g., 2025-10-01).");
                        continue;
                    }
                        Task newTask = new Task(id, taskName, description, status, priority, dueDate);
                        taskService.createTask(newTask);
                        System.out.println("Task created successfully!");
                        break;
                case 2:
                    System.out.print("Enter task ID to delete: ");
                    int deleteId = sc.nextInt();
                    taskService.deleteTask(deleteId);
                    break;
                case 3:
                    System.out.print("Enter task ID to update status: ");
                    int updateId = sc.nextInt();
                    System.out.print("Enter new status: ");
                    String status1 = sc.next();
                    taskService.updateTaskStatus(updateId, status1);
                    break;
                case 4:
                    System.out.print("Enter task ID to find: ");
                    int findId = sc.nextInt();
                    taskService.printTaskById(findId);
                    break;
                case 5:
                    System.out.print("Enter updated task ID: ");
                    int updatedId = sc.nextInt();
                    // Assuming you have a method to get updated Task details
                    Task updatedTask = taskService.findById(updatedId);
                    taskService.updateTask(updatedTask);
                    break;
                case 6:
                    taskService.clearTasks();
                    break;
                case 7:
                    taskService.printAllTasks();
                    break;
                case 8:
                    taskService.printAllTasksSortedByPriority();
                    break;
                case 9:
                    taskService.printAllTasksSortedByDueDate();
                    break;
                case 10:
                    taskService.printAllTasksSortedByStatus();
                    break;
                case 11:
                    taskService.printAllTasksSortedByName();
                    break;
                case 12:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }


    }
}
