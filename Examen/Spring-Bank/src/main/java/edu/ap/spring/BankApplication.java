package edu.ap.spring;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import edu.ap.spring.jpa.*;

@SpringBootApplication
public class BankApplication {

	@Autowired
	private BankRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Bean
  	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return (args) -> {
			Wallet walletA = new Wallet("walletA", 50.0);
	 		repository.save(walletA);
	 		Wallet walletB = new Wallet("walletB", 50.0);
	 		repository.save(walletB);
		};
  	}
}
