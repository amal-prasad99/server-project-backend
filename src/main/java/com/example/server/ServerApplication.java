package com.example.server;

import com.example.server.enumaration.Status;
import com.example.server.model.Server;
import com.example.server.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.server.enumaration.Status.SERVER_UP;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
	@Bean
	CommandLineRunner run(ServerRepo serverRepo){
		return args -> {
			serverRepo.save(new Server(
					null,
					"192.168.1.1",
					"Ubuntu",
					"8 GB",
					"http://localhost:8080/server/image/s1.png",
					SERVER_UP
			));
			serverRepo.save(new Server(
					null,
					"192.168.1.2",
					"Win",
					"23 GB",
					"http://localhost:8080/server/image/s2.png",
					SERVER_UP
			));
			serverRepo.save(new Server(
					null,
					"192.168.1.3",
					"XP",
					"46 GB",
					"http://localhost:8080/server/image/s3.png",
					SERVER_UP
			));
			serverRepo.save(new Server(
					null,
					"192.168.1.4",
					"Linux",
					"67 GB",
					"http://localhost:8080/server/image/s4.png",
					SERVER_UP
			));

		};
	}

}
