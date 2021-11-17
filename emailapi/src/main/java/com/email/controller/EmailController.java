package com.email.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.email.service.EmailService;

@Controller
public class EmailController {
	
	Logger logger = LoggerFactory.getLogger(EmailController.class);
	
	@Autowired
	private EmailService service;
	
	// Home page of form
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(){
		logger.info("Opening form ...");
		return "home";
	}
	
	// ValidateOTP page handler
	@RequestMapping(value="/validateOTP",method=RequestMethod.POST)
	public String validate(@RequestParam("emailId") String emailId) {
		service.sendEmail(emailId);
		logger.info("Inside Validation ...");
		return "validate";
	}
	
	// Status page handler
	@RequestMapping(value="/result", method=RequestMethod.POST)
	public String status(@RequestParam("otp") String otp, @RequestParam("emailId") String emailId) {
			if(service.validateOTP(Integer.parseInt(otp), emailId).equals("true")) {
				logger.info("OTP matched is valid ...");
				service.deleteData(emailId);
				logger.info("Safe Exit ...");
				return "valid";
			}
			else if(service.validateOTP(Integer.parseInt(otp), emailId).equals("OtpExpired")) {
				logger.info("OTP expired ...");
				return "otpexpired";
			}
			else {
				service.deleteData(emailId);
				logger.info("OTP is invalid ...");
				logger.info("Safe Exit ...");
				return "invalid";
			}
				
	}
	
}