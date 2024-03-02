package com.example.demo;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {

	private final UserDao userDao;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public DemoApplication(UserDao userDao, PasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	public void init() {
		userDao.save(new User("Piotr", "Piotrowski", "admin1234",
				passwordEncoder.encode("admin1234")));
		userDao.save(new User("ania", "Annowska", "ania1234",
				passwordEncoder.encode("ania1234")));
	}
}
