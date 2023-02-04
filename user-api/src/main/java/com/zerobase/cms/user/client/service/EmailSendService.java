package com.zerobase.cms.user.client.service;

import com.zerobase.cms.user.client.MailgunClient;
import com.zerobase.cms.user.client.mailgun.SendMailForm;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSendService {

    private final MailgunClient mailgunClient;

    public void sendEmail() {

        SendMailForm form = SendMailForm.builder()
                .from("zerobase-test@gmail.com")
                .to("heyazoo1007@gmail.com")
                .subject("test email - from zerobase")
                .text("my text")
                .build();
        mailgunClient.sendEmail(form);
    }
}
