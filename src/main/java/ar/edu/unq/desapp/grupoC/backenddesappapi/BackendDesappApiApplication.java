package ar.edu.unq.desapp.grupoC.backenddesappapi;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BackendDesappApiApplication {
	public static Logger logger = LogManager.getLogger(BackendDesappApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BackendDesappApiApplication.class, args);
		BasicConfigurator.configure();
	}
}
