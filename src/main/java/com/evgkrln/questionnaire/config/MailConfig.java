package com.evgkrln.questionnaire.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.protocol}")
    private String protocol;
    @Value("${mail.debug}")
    private String debug;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String auth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String enable;

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.host);
        mailSender.setUsername(this.username);
        mailSender.setPassword(this.password);
        mailSender.setPort(this.port);
        Properties properties = mailSender.getJavaMailProperties();
        properties.setProperty("mail.transport.protocol", this.protocol);
        properties.setProperty("mail.debug", this.debug);
        properties.setProperty("mail.smtp.auth", this.auth);
        properties.setProperty("mail.smtp.starttls.enable", this.enable);
        return mailSender;
    }
}

