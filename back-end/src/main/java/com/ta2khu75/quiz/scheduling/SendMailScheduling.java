package com.ta2khu75.quiz.scheduling;

import java.util.ArrayList;
import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ta2khu75.quiz.enviroment.EmailTemplateEnv;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Component
@RequiredArgsConstructor
public class SendMailScheduling {
	private final JavaMailSender javaMailSender;
	private final List<MimeMessage> list = new ArrayList<>();

	public void addMail(MimeMessage mail) {
		list.add(mail);
	}

	public void addAllMail(List<MimeMessage> listMail) {
		list.addAll(listMail);
	}
	public void addMail(String to, String subject, String text, boolean isHtml) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setTo(to);
		helper.setText(text, isHtml);
		helper.setSubject(subject);
		list.add(message);
	}
	@Scheduled(fixedDelay = 5000)
	public void run() throws MessagingException {
//		MimeMessage message = javaMailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
//		helper.setTo("boomkings474@gmail.com");
//		helper.setText(EmailTemplateEnv.EMAIL_VERIFICATION, true);
//		helper.setSubject("Email Verification");
//		javaMailSender.send(message);
		while (!list.isEmpty()) {
			MimeMessage mail = list.remove(0);
			try {
				javaMailSender.send(mail);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
		}
	}
}
