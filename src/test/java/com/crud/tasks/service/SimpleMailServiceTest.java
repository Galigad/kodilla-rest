package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SimpleMailServiceTest {

    @InjectMocks
    private SimpleMailService simpleMailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail() {
        //Given
        Mail mail = new Mail("test@test.com", null, "Test", "Test Message");

        SimpleMailMessage mailMassage = new SimpleMailMessage();
        mailMassage.setTo(mail.getMailTo());
        mailMassage.setCc(mail.getToCc());
        mailMassage.setSubject(mail.getSubject());
        mailMassage.setText(mail.getMessage());

        //When
        simpleMailService.send(mail);

        //Then
        verify(javaMailSender, times(1)).send(mailMassage);
    }

    }
