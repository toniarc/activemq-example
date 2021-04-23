package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.service.SessionService;

@Controller
public class WebController {

	@Autowired
	private SessionService service;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void sendMessages() {
		service.createSession();
	}
	
	@GetMapping("/index")
	public String greeting() {
		return "index";
	}

}
