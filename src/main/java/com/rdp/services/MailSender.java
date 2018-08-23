package com.rdp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author saurav patar
 *
 */
@Service
public class MailSender {

    Logger LOGGER = LoggerFactory.getLogger(MailSender.class);

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendEmail(String from, String to, String subject, String body) {

//        SimpleMailMessage mail = new SimpleMailMessage();
//        mail.setFrom(from);
//        mail.setTo(to);
//        mail.setSubject(subject);
//        mail.setText(body);
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message
                    ,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            messageHelper.addAttachment("logo.png", new ClassPathResource("Applogo.png"));

            Context context = new Context();
            Map variables = new HashMap<>();
            variables.put("name","saurav");
            variables.put("signature","saurav kumar");
            variables.put("location","ranchi");
            context.setVariables(variables);
            String html = templateEngine.process("email-template", context);

            LOGGER.info("Sending Email");

            //javaMailSender.send(mail);
            messageHelper.setTo(to);
            messageHelper.setFrom(from);
            messageHelper.setSubject(subject);
            messageHelper.setText(html, true);

            javaMailSender.send(message);

            LOGGER.info("Mail Send");
        }catch (MessagingException e){
            e.printStackTrace();
        }


    }
}
