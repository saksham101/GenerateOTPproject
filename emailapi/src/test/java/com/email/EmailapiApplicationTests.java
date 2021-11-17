package com.email;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.email.dao.OTPdao;
import com.email.entities.OTP;
import com.email.service.EmailService;

@SpringBootTest
class EmailapiApplicationTests {
	
	@Autowired
	public EmailService service;
	
	@Autowired
	public OTPdao otpDao;

	@Test
	void testOTPLength() {
		int expectedResult = 6;
		int actualResult = String.valueOf(service.generateOTP()).length();
		assertThat(expectedResult).isEqualTo(actualResult);
	}
	
	@Test
	void checkOTPFormat() {
		String expectedResult = "^\\s*(?:\\S\\s*){0,9}$";
		int actualResult = service.generateOTP();
		assertEquals(true, String.valueOf(actualResult).matches(expectedResult));
	}
	
	@Test
	void isEmailSent(){
		boolean expectedResult = true;
		boolean actualResult = service.sendEmail("gargsaksham101@gmail.com");
		assertThat(expectedResult).isEqualTo(actualResult);
		service.deleteData("gargsaksham101@gmail.com");
	}
	
	@Test
	void isDeleteData() {
		boolean expectedResult = true;
		service.sendEmail("gargsaksham101@gmail.com");
		boolean actualResult = service.deleteData("gargsaksham101@gmail.com");
		assertThat(expectedResult).isEqualTo(actualResult);
	}
	
}
