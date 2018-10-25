package com.basha.aws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basha.aws.service.SqsService;

@RestController
public class SqsController {
	
	@Autowired
	SqsService service;
	
	@RequestMapping("/message")
	public String getMessage(){
		
		return service.getMessage();
		
	}
	
	@RequestMapping("/message/{message}")
	public void sendMessage(@PathVariable("message") String message){
		
		 service.sendMessage(message);
		
	}

}
