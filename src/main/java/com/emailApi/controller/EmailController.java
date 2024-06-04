package com.emailApi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emailApi.model.EmailRequest;
import com.emailApi.model.EmailResponse;
import com.emailApi.service.EmailService;

@RestController
@CrossOrigin
public class EmailController {

	@Autowired
	private EmailService emailService;

	@GetMapping("/home")
	public String home() {

		return "home page";
	}

	// api to send eamil
	// @RequestMapping(value = "/sendemail",method = RequestMethod.POST)
	@PostMapping("/sendemail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) throws IOException

	{

		boolean sendEamil = emailService.sendEamil(emailRequest.getTo(), emailRequest.getSubject(),
				emailRequest.getMessage());

		if(sendEamil)
		{
			return ResponseEntity.ok(new EmailResponse("Email is sent successfully......"));
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email Not Sent");

	}

	// api to send eamil
	// @RequestMapping(value = "/sendemailwithattachment",method =
	// RequestMethod.POST)
	@PostMapping("/sendemailwithattachment")
	public ResponseEntity<?> sendEmailWithAttachment(@RequestBody EmailRequest emailRequest) throws IOException

	{

		boolean sendEamil = emailService.sendEamilWithAttachment(emailRequest.getTo(), emailRequest.getSubject(),
				emailRequest.getMessage());

		System.out.println(sendEamil);

		return ResponseEntity.ok("sent");

	}
}
