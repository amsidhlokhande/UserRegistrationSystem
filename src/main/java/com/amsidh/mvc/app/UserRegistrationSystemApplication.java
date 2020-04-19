package com.amsidh.mvc.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.amsidh.mvc.config.SpringBeansConfig;
import com.amsidh.mvc.controller.UserRegistrationController;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.amsidh.mvc.repositories" })
@EntityScan(basePackages = { "com.amsidh.mvc.entities" })
@ComponentScan(basePackages = { "com.amsidh.mvc" }, basePackageClasses = {UserRegistrationController.class})
public class UserRegistrationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(new Class[]{UserRegistrationSystemApplication.class, SpringBeansConfig.class}, args);
	}

}
