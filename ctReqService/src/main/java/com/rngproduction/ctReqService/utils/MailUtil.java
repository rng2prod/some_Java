package com.rngproduction.ctReqService.utils;

import com.rngproduction.ctReqService.config.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Component
public class MailUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailUtil.class);

    @Autowired
    Property property;

    public void sendMail(String sender,
                         String mailReceivers,
                         String subject,
                         String fileName,
                         String file) {

        final String username = property.getUsername();
        final String password = property.getUserpass();

        Properties pr = new Properties();
        pr.put("mail.smtp.auth", property.authMailServer());
        pr.put("mail.smtp.host", property.getMailHost());
        pr.put("mail.smtp.port", property.getMailPort());
        pr.put("mail.smtp.ssl.trust", property.getMailHost());
        pr.put("mail.smtp.starttls.enable", true);

        Session session;
        if (property.authMailServer() && !username.isEmpty() && !password.isEmpty()) {
            session = Session.getInstance(pr,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
        } else {
            session = Session.getInstance(pr);
        }

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailReceivers));
            message.setSubject(subject);

            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            FileDataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            Transport.send(message);
            LOGGER.info("Success mail send");

        } catch (MessagingException e) {
            LOGGER.error("Unsuccessful mail send");
            LOGGER.error(e.getMessage());
        }
    }
}
