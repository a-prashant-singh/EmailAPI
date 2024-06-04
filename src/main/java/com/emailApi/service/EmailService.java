package com.emailApi.service;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public boolean sendEamil(String to, String subject, String message) {

		boolean flag = false;
		String from = "digitalcurrencybazar@gmail.com";
		// variable for gmail
		String host = "smtp.gmail.com";

		// Get the system properties
		Properties properties = new Properties();
		System.out.println(properties);

		// setting important information to property object

		// set host
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Step 1> to get the session object
		Session session = Session.getDefaultInstance(properties, new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("digitalcurrencybazar@gmail.com", "scbretuponojbdiw");
			}

		});

		session.setDebug(true);
		

		

		try {
			if(session!=null)
			{
			// Step 2> Compose the message [text , multimedia]
			Message m = new MimeMessage(session);

			// from email
			m.setFrom(new InternetAddress(from));

			// adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject to message
			m.setSubject(subject);

			// adding text to message
			m.setText(message);

			// send

			// Step 3> send the message using transport class
			
		
			
			Transport.send(m);
			
			flag = true;
			System.out.println("sent successfully........");
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return flag;
	}

	public boolean sendEamilWithAttachment(String to, String subject, String message) throws IOException {

		File file = new File("C:\\Users\\amit4\\Desktop\\Prashant_Form15G.pdf.docx");
		boolean flag = false;
		String from = "digitalcurrencybazar@gmail.com";
		// variable for gmail
		String host = "smtp.gmail.com";

		// Get the system properties
		Properties properties = new Properties();
		System.out.println(properties);

		// setting important information to property object

		// set host
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Step 1> to get the session object
		Session session = Session.getDefaultInstance(properties, new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("digitalcurrencybazar@gmail.com", "scbretuponojbdiw");
			}

		});

		session.setDebug(true);

		// Step 2> Compose the message [text , multimedia]
		Message m = new MimeMessage(session);

		try {

			// from email
			m.setFrom(new InternetAddress(from));

			// adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject to message
			m.setSubject(subject);

			MimeBodyPart part1 = new MimeBodyPart();
			part1.setText(message);

			MimeBodyPart part2 = new MimeBodyPart();
			part2.attachFile(file);

			MimeMultipart mimeMultipart = new MimeMultipart();
			mimeMultipart.addBodyPart(part1);
			mimeMultipart.addBodyPart(part2);

			// adding text to message
			m.setContent(mimeMultipart);

			// send

			// Step 3> send the message using transport class
			Transport.send(m);
			flag = true;
			System.out.println("sent successfully with attachment........");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return flag;
	}

}
