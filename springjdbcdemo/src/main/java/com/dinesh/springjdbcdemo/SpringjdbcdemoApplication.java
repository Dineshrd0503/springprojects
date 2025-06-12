package com.dinesh.springjdbcdemo;

import com.dinesh.springjdbcdemo.model.Alien;
import com.dinesh.springjdbcdemo.repo.AlienRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringjdbcdemoApplication {

	public static void main(String[] args) {

		ApplicationContext context= SpringApplication.run(SpringjdbcdemoApplication.class, args);
		Alien alien1 = context.getBean(Alien.class);
		alien1.setId(111);
		alien1.setName("Dinesh");
		alien1.setTech("Java");
		System.out.println(alien1.getId());

		AlienRepo repo= context.getBean(AlienRepo.class);
		repo.save(alien1);

	}

}
