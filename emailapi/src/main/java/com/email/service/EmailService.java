package com.email.service;

import java.util.Date;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.email.controller.EmailController;
import com.email.dao.OTPdao;
import com.email.entities.OTP;

@Service
public class EmailService implements email {
	
	@Autowired
	private JavaMailSender javaMailSender;
    
	@Autowired
	private OTPdao otpDao;
	
	private static final long OTP_DURATION =  60 * 1000;
	Logger logger = LoggerFactory.getLogger(EmailService.class);
	OTP OTPobj = new OTP();
	
	@Override
	public boolean sendEmail(String sendTo)
	{
		int OTPgenerated = generateOTP();
		String sendFrom = "gargsaksham101@gmail.com";
			
		SimpleMailMessage msg = new SimpleMailMessage();
		Scanner sc = new Scanner(System.in);
		
		logger.info("Sending OTP ...");
		
		msg.setTo(sendTo);
		msg.setFrom(sendFrom);
		msg.setSubject("OTP for Verification");
		msg.setText("OTP is: "+OTPgenerated);
		OTPobj.setEmailId(sendTo);
		OTPobj.setOtp(OTPgenerated);
		OTPobj.setGeneratedDate(new Date());
		
		otpDao.save(OTPobj);
		
		javaMailSender.send(msg);

		logger.info("OTP Sent ...");
        sc.close();
        return true;
	}
	
	@Override
	public String validateOTP(int userOTP, String userEmailId) 
	{
		long currentTime = System.currentTimeMillis();
		
		long otpRequestedTime = OTPobj.getGeneratedDate().getTime();
		if(userEmailId.equals(OTPobj.getEmailId()) && (otpRequestedTime + OTP_DURATION > currentTime)) {
			if(userOTP == OTPobj.getOtp()) {
				return "true";
			}
		}
		else if(otpRequestedTime + OTP_DURATION < currentTime)
		{
			return "OtpExpired";
		}
		else {
			return "false";
		}
		return "false";		
	}
	
	
	
	@Override
	public boolean deleteData(String emailId) 
	{
		OTP tempObj = otpDao.findById(emailId).orElse(null);
		otpDao.delete(tempObj);
		return true;
	}
	
	@Override
	public int generateOTP() {
		return (int) ((Math.random()*900000)+100000);
	}

}