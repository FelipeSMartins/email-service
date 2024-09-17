package com.martins.email_service.controllers;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martins.email_service.application.EmailSenderService;
import com.martins.email_service.core.EmailRequest;
import com.martins.email_service.core.exceptions.EmailServiceException;

@RestController
@RequestMapping("/api/email")
public class EmailSenderController {
	
	private final EmailSenderService emailSenderService;
	
	@Autowired
	public EmailSenderController(EmailSenderService emailService) {
		this.emailSenderService = emailService;
	}
	
	@PostMapping()
	public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request){
		try {
			this.emailSenderService.sendEmail(request.to(), request.subject(), request.body());
			return ResponseEntity.ok("email send sucessfully");
		} catch (EmailServiceException ex) {
			return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Error while sending email");
		}
	}

}
