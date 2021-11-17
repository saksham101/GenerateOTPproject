package com.email;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.email.service.EmailService;

@SpringBootApplication
public class EmailapiApplication {

	@Autowired
	private EmailService ec;
	public static void main(String[] args) throws MessagingException{
		
		SpringApplication.run(EmailapiApplication.class, args);
			
	}
}
