package com.zerobase.cms.user.client.mailgun;

import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendMailForm {
    private String from;
    private String to;
    private String subject;
    private String text;
}
