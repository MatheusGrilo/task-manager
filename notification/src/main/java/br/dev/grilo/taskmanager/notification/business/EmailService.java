package br.dev.grilo.taskmanager.notification.business;

import br.dev.grilo.taskmanager.notification.business.dto.TasksDTO;
import br.dev.grilo.taskmanager.notification.infra.exceptions.EmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Value("${email.sender}")
    public String from;

    @Value("${email.senderName}")
    public String senderName;

    public void sendEmail(TasksDTO tasksDTO) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());

            helper.setFrom((new InternetAddress(from, senderName)));
            helper.setTo(InternetAddress.parse(tasksDTO.getUserEmail()));
            helper.setSubject("Task Manager Notification");

            Context context = new Context();
            context.setVariable("taskName", tasksDTO.getTaskName());
            context.setVariable("eventDate", tasksDTO.getEventDate());
            context.setVariable("taskDescription", tasksDTO.getTaskDescription());
            String template = templateEngine.process("notification", context);
            helper.setText(template, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new EmailException("Error while sending email: ", e.getCause());
        }
    }


}
