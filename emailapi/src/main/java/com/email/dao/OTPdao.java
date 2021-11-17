package com.email.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.email.entities.OTP;

@Repository
public interface OTPdao extends JpaRepository<OTP, String>
{

}