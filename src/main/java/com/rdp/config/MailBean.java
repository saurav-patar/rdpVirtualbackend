package com.rdp.config;

import com.rdp.services.MailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author saurav patar
 *
 */
@Configuration
public class MailBean {

    @Bean
    public MailSender configureMail(){
        return new MailSender();
    }
}
