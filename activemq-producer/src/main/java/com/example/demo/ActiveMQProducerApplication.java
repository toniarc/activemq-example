package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@ComponentScan({ "com.example.demo", "br.com.voxdatati.auditlib" })
@EnableAspectJAutoProxy
@SpringBootApplication
public class ActiveMQProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActiveMQProducerApplication.class, args);
	}

}
