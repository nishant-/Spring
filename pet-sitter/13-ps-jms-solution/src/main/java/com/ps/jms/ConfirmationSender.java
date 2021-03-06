package com.ps.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ConfirmationSender {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public ConfirmationSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(final Confirmation confirmation) {
        jmsTemplate.convertAndSend("confirmationQueue", confirmation);
    }
}
