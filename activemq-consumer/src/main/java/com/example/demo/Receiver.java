package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Notification;
import com.example.demo.service.NotificationService;

@Component
public class Receiver {

  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

  @Autowired
  private JmsTemplate JmsTemplate;
  
  @Autowired
  private NotificationService service;
  
  @JmsListener(destination = "filademensagens")
  public void receive(String message) {
    Notification notification = service.save(message);

    LOGGER.info("send notification: " + notification);
    JmsTemplate.convertAndSend("notification", notification);
  }
}