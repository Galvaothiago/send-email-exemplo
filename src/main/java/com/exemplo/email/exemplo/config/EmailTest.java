package com.exemplo.email.exemplo.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.exemplo.email.exemplo.service.Email;
import com.exemplo.email.exemplo.service.SendEmailService;

@Configuration
public class EmailTest implements CommandLineRunner{

	@Autowired
	private SendEmailService sendEmail;
	
	@Override
	public void run(String... args) throws Exception {
		
		var email = new Email();
		Set<String> recipient = new HashSet<>();
		
		recipient.add("thiago.sgalvao@telefonica.com");
		
		Map<String, Object> variables = new HashMap<>();

		String password = "235689";
		variables.put("password", password);
		
		email.setVariables(variables);
		email.setSubject("Sua senha foi alterada " + "- Portal AST");
		email.setRecipients(recipient);
		email.setBody("template-email.html");
		
		
		sendEmail.toSend(email);
	}

}
