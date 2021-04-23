package com.example.demo;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class ProducerController {

	@Autowired
	private Publisher<Message<String>> jmsReactiveSource;
	
	@GetMapping(value = "/notifications", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getNotifications() {
        return Flux.from(jmsReactiveSource)
        		//.log()
        		.map( msg -> { 
        			return msg.getPayload();
        		});
    }
	
}
