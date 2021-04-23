package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Notification;
import com.example.demo.repository.NotificationRepository;

import br.com.voxdatati.auditlib.annotations.Auditavel;
import br.com.voxdatati.auditlib.aspect.DataProvider;

@Service
public class NotificationService {

	@Autowired
	private NotificationRepository repo;
	
	@Autowired
	private DataProvider dataProvider;
	
	@Auditavel
	public Notification save(String message) {
		Notification notification = new Notification(message, new Date());
		dataProvider.data(notification);
		return repo.save(notification);
	}
}
