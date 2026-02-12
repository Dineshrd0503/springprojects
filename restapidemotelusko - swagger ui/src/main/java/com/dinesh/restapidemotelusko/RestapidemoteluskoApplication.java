package com.dinesh.restapidemotelusko;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Electronic Items API", version = "1.0", description = "API for managing electronic items"),
		servers = @Server(url = "http://localhost:8080/restapitelusko", description = "Local Server")
)
public class RestapidemoteluskoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapidemoteluskoApplication.class, args);
	}

}
