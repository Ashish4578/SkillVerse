package com.skillverse.notificationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEnrollmentEmail(Long userId, Long courseId) {

        String to = "test@example.com"; //  temporary (later fetch from UserService)
        String subject = "Course Enrollment Successful 🎉";

        String body = String.format(
                "Hello User %d,\n\nYou have successfully enrolled in course %d.\n\nHappy Learning!\n\nSkillVerse Team 🚀",
                userId, courseId
        );

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);

        log.info("📧 Email sent to userId={} for courseId={}", userId, courseId);
    }
}