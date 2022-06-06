package ru.edu.space.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.logger.ELogger;
import ru.edu.space.app.logger.IELog;
import ru.edu.space.app.service.user.UserTokenService;

import java.util.UUID;

/**
 * @author agerus
 */
@Component
public class RegistrationComponent implements ApplicationListener<RegistrationEvent> {

    @Value("${email}")
    private String fromEmail;

    private final UserTokenService userTokenService;
    private static final String PREFIX = "[RegistrationComponent] ";
    protected static IELog log = ELogger.getLogger(RegistrationComponent.class);

    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    public RegistrationComponent(UserTokenService userTokenService) {
        this.userTokenService = userTokenService;
    }

    @Override
    public void onApplicationEvent(RegistrationEvent registrationEvent) {
        User user = registrationEvent.getUser();
        final String token = UUID.randomUUID().toString();
        userTokenService.createToken(user, token);
        sendEmail(registrationEvent, user, token);
    }

    private void sendEmail(RegistrationEvent registrationEvent, User user, String token) {
        final String recipientAddress = user.getEmail();
        final String subject = "Подтверждение регистрации";
        final String confirmationUrl = registrationEvent.getAppUrl().concat("/registration/registration-confirm?token=").concat(token);
//        final String message = messages.getMessage("message.regSucc", null, new Locale("RU"));
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText("message".concat(" \r\n").concat(confirmationUrl));
        email.setFrom(fromEmail);
        mailSender.send(email);
    }
}
