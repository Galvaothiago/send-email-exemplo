package com.exemplo.email.exemplo.infrastructure;
import java.io.IOException;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.exemplo.email.exemplo.service.Email;
import com.exemplo.email.exemplo.service.SendEmailService;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class SmtpSendEmailService implements SendEmailService{

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Configuration freemakerConfig;
	
	@Override
	public void toSend(Email email) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom("Portal AST <thiagogalvaodev@outlook.com>");
			helper.setTo(email.getRecipients().toArray(new String[0]));
			helper.setSubject(email.getSubject());
			helper.setText(templateHtml(email), true);
			
			mailSender.send(mimeMessage);
			System.out.println("Email sent successfully");
		} catch(Exception e) {
			System.out.println("Error mail: " + e.getMessage());
		}
		
	}
	
	private String templateHtml(Email email) {
		try {
			Template template = freemakerConfig.getTemplate(email.getBody());
			
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, email.getVariables());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

}
