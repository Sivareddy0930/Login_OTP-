package com.otp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otp.entity.Login;

public interface LoginRepositoryInterface extends JpaRepository<Login, Long> {

	Login findByOtp(String otp);

	Login findByEmail(String email);
	
	

}
