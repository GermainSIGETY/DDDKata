package fr.sauceDallas.getThingsDone.todos.infra.emailSender;

import fr.sauceDallas.getThingsDone.alerts.infrastructure.EmailAlertSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;

public class EmailAlertSenderImpl implements EmailAlertSender {

    private static final String TEMPLATE_NAME = "todoAlertEmail";

    private JavaMailSender mailSender;

    private TemplateEngine templateEngine;

    public EmailAlertSenderImpl(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendEmail(String dest, String title, LocalDateTime dueDate) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            mimeMessage.setContent(generateContent(title, dueDate), "text/html;charset=utf-8");
            mimeMessage.setSubject("A Todo for you");
            mimeMessage.setFrom(new InternetAddress("noreply@saucedallas.fr", "saucedallas.fr"));
            mimeMessage.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(dest));
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    private Object generateContent(String title, LocalDateTime dueDate) {
        Context context = new Context();
        context.setVariable("title", title);
        context.setVariable("dueDate", dueDate);
        return templateEngine.process(TEMPLATE_NAME, context);
    }
}
