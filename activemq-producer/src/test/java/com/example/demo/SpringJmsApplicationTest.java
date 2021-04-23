package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringJmsApplicationTest {

	//@Rule
	//public EmbeddedActiveMQResource resource = new EmbeddedActiveMQResource();

	@Autowired
	private Sender sender;

	@Test
	public void testReceive() throws Exception {
		for (int i = 0; i < 1000; i++) {
			sender.send(i + "Mensagem do ActiveMQ Artemis!");
		}

	}
}