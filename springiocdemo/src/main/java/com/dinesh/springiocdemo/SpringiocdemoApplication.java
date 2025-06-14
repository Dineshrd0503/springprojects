package com.dinesh.springiocdemo;

import com.dinesh.springiocdemo.controller.TaskController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringiocdemoApplication {

	public static void main(String[] args) {

		ApplicationContext conext=SpringApplication.run(SpringiocdemoApplication.class, args);
		TaskController taskController = conext.getBean(TaskController.class);
		taskController.start();
	}

}
