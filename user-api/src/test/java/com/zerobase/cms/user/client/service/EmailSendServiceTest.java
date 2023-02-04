package com.zerobase.cms.user.client.service;

import com.zerobase.cms.user.client.MailgunClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailSendServiceTest {

    @Autowired
    private MailgunClient mailgunClient;

    @Test
    void EmailTest() {
        // 숙제
        mailgunClient.sendEmail(null);

        // String response = emailSendService.sendEmail;
    }
}
