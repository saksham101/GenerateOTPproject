package com.email.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OTP {

	@Id
	private String emailId;
	private int otp;
	private Date generatedDate;
	
	@Override
	public String toString() {
		return "OTP [emailId=" + emailId + ", otp=" + otp + ", generatedDate=" + generatedDate + "]";
	}
	
	public Date getGeneratedDate() {
		return generatedDate;
	}
	public void setGeneratedDate(Date generatedDate) {
		this.generatedDate = generatedDate;
	}
	public OTP() {
		super();
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public OTP(String emailId, int otp, Date generatedDate) {
		super();
		this.emailId = emailId;
		this.otp = otp;
		this.generatedDate = generatedDate;
	}
	

	
	
}
