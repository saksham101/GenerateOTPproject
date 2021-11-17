package com.email.service;

import java.util.Date;

import com.email.entities.OTP;

public interface email {

	public boolean sendEmail(String sendTo);

	boolean deleteData(String emailId);

	String validateOTP(int userOTP, String userEmailId);

	int generateOTP();
}
