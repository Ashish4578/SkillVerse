package com.skillverse.notificationservice.service;

import com.skillverse.notificationservice.model.EnrollmentEvent;
import com.skillverse.notificationservice.model.UserCreatedEvent;
import com.skillverse.notificationservice.repo.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private NotificationRepository notificationRepository;
    private final JavaMailSender mailSender;

    public void sendEnrollmentEmail(EnrollmentEvent enrollmentEvent) {
        //for sms
        String smsMessage = String.format(
                "Hello %s, you have successfully enrolled in course %s. Happy Learning! - SkillVerse Team 🚀",
                enrollmentEvent.getUser().getUsername(), enrollmentEvent.getCourse().getCourseName()
        );
//        enrollmentEvent.getContactNumber();


        //for email
        String body = String.format(
                "Hello %s,\n\n" +
                 "\tThank you for choosing SkillVerse Platform.\n" +
                 "\tYou have successfully enrolled in course %s.\n" +
                 "\tHappy Learning!\n\n" +
                        "Best Regards,\n" +
                 "SkillVerse Team 🚀",
                enrollmentEvent.getUser().getUsername(),enrollmentEvent.getCourse().getCourseName()
        );

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(enrollmentEvent.getUser().getEmail());
        message.setSubject(enrollmentEvent.getMessage());
        message.setText(body);
        mailSender.send(message);

        log.info("📧 Email sent to userId={} for courseId={}", enrollmentEvent.getUser().getUsername(), enrollmentEvent.getCourse().getCourseName());
    }
    public void freshAccountCreated(UserCreatedEvent userCreatedEvent) {
        String body = String.format(
                "Hello %s,\n\n" +
                        "\tWelcome to SkillVerse Platform.\n" +
                        "\tYour account has been successfully created.\n" +
                        "\tHappy Learning!\n\n" +
                        "Best Regards,\n" +
                        "SkillVerse Team 🚀",
                userCreatedEvent.getUsername()
        );
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userCreatedEvent.getEmail());
        message.setSubject("Welcome to SkillVerse!");
        message.setText(body);
        mailSender.send(message);
        log.info("📧 Welcome email sent to userId={}", userCreatedEvent.getUsername());
    }
}