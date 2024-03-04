package com.otp.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otp.entity.User;
import com.otp.payload.UserRequestDTO;
import com.otp.payload.UserResponseDTO;
import com.otp.repository.UserRepositoryInterface;

@Service
public class UserService {
	
	@Autowired
	UserRepositoryInterface userRepositoryInterface;
	
	@Autowired
	EmailService emailService;
	
	public UserResponseDTO registerUser( UserRequestDTO requestDto){
		
		UserResponseDTO responeDto = new UserResponseDTO();
		
		
		User existingUser = userRepositoryInterface.findByEmail(requestDto.getEmail());
		
		if(existingUser != null) {
			if(existingUser.isVerfied() == false){
				userRepositoryInterface.delete(existingUser);
				
				Random r = new Random();
				
				String otpNumber = String.format("%04d", r.nextInt(10000));
				
				
				User newRegistration = new User();
				newRegistration.setUser_name(requestDto.getUser_name());
				newRegistration.setEmail(requestDto.getEmail());
				newRegistration.setOtp(otpNumber); 
				newRegistration.setVerfied(false);
				
				User savedUser = userRepositoryInterface.save(newRegistration);
				
				String subject="Email Verifaction";
				String body = "your Verifaction OTP is "+ savedUser.getOtp();
				
				emailService.sendMail(savedUser.getEmail(),subject , body);
				
				

				responeDto.setMessage("OTP sent successfully");
				
			}
			else {
				responeDto.setMessage("Account Already Exist and Verified");
			}
		}else {
			Random r = new Random();
			
			String otpNumber = String.format("%04d", r.nextInt(10000));
			
			
			User newRegistration = new User();
			newRegistration.setUser_name(requestDto.getUser_name());
			newRegistration.setEmail(requestDto.getEmail());
			newRegistration.setOtp(otpNumber); 
			newRegistration.setVerfied(false);
			
			User savedUser = userRepositoryInterface.save(newRegistration);
			
			String subject="Email Verifaction";
			String body = "your Verifaction OTP is "+ savedUser.getOtp();
			
			emailService.sendMail(savedUser.getEmail(),subject , body);
			
			

			responeDto.setMessage("OTP sent successfully");
		}
				
		return responeDto; 
		
	}
	
	
	
	public String verifyUser(String email,String otp) {
		
		String response="";
		
		User user = userRepositoryInterface.findByEmail(email);
				
		if(user != null && user.isVerfied()) {
			response="User is Already verified";
		}else if(otp.equals(user.getOtp())){
			
			user.setVerfied(true);
			user.setOtp(null);
			userRepositoryInterface.save(user);
						
			response="User verified Successfully";
			
			
		}
		else {
			response="User not  verified";
		}
		
		return response;
		
	}

}
