package com.example.demo.config;

import javax.jms.ConnectionFactory;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;

@Configuration
@EnableJms
public class JmsConfig {

	@Autowired
	private ConnectionFactory connectionFactory;
	
	/*
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		//converter.setTypeIdPropertyName("_type");
		return converter;
	}*/
	
    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        return jmsTemplate;
    }
    
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        //factory.setDestinationResolver(destinationResolver());
        factory.setSessionTransacted(true);
        factory.setConcurrency("3-10");
        return factory;
    }
	
	@Bean
    public Publisher<Message<String>> jmsReactiveSource() {
        return IntegrationFlows
                .from(Jms.messageDrivenChannelAdapter(connectionFactory)
                        .destination("notification")
                	)
                .channel(MessageChannels.queue())
                .log(LoggingHandler.Level.DEBUG)
                //.log()
                .toReactivePublisher();
    }
	
	
}