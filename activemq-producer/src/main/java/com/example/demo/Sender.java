package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

  //private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

  @Autowired
  private JmsTemplate jmsTemplate;

  public void send(String message) {
    //LOGGER.info("sending message='{}'", message);
    jmsTemplate.convertAndSend("filademensagens", message);
  }
}