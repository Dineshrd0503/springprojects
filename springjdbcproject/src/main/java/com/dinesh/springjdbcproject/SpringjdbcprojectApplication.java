package com.dinesh.springjdbcproject;

import com.dinesh.springjdbcproject.controller.ProductController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringjdbcprojectApplication {

	public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SpringjdbcprojectApplication.class, args);

        ProductController productController = context.getBean(ProductController.class);
        productController.start();
	}

}
