package com.Utilities_Backend.controller;

import com.Utilities_Backend.repository.feederRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Utilities_Backend.repository.receivingRepository;


@RestController
@RequestMapping("/api")
public class Controller {
	 @Autowired
	    private  receivingRepository receiveRepository;

	@Autowired
	private feederRepository feederRepo;
	 
}
