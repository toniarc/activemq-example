package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Sender;
import com.example.demo.annotation.LogExecutionTime;
import com.example.demo.entity.Session;
import com.example.demo.repository.SessionRepository;

import br.com.voxdatati.auditlib.annotations.Auditavel;
import br.com.voxdatati.auditlib.aspect.DataProvider;

@Service
public class SessionService {

	@Autowired
	private SessionRepository sessionRepository;
	
	@Autowired
	private Sender sender;
	
	@Autowired
    DataProvider dataProvider;
	
	@Auditavel
	@LogExecutionTime
	@Transactional(rollbackFor = Throwable.class)
	public void createSession() {
		Session session = sessionRepository.save(new Session(new Date()));
		
		dataProvider.data(session);
		
		for(int i=1;i<=100;i++) {
			sender.send("session: " + session.getId() +  " mensagem " + i);
		}
	}
}
