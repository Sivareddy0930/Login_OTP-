package com.otp.service;


import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otp.entity.Login;
import com.otp.entity.User;
import com.otp.payload.UserLoginVerifiyDTO;
import com.otp.payload.UserVerifiyDTO;
import com.otp.payload.UserloginDTO;
import com.otp.repository.LoginRepositoryInterface;
import com.otp.repository.UserRepositoryInterface;

import jakarta.transaction.Transactional;

@Service
public class LoginService {
	
	@Autowired
	UserRepositoryInterface userRepositoryInterface;
	
	@Autowired
	LoginRepositoryInterface loginRepositoryInterface;
	
	@Autowired
	EmailService emailService;
	
	public String LoginVerifiy( UserloginDTO userloginDTO ) {
		
		User user = userRepositoryInterface.findByEmail(userloginDTO.getEmail() );
		
		if(user !=null) {
			
			Login userInLoginTable = loginRepositoryInterface.findByEmail(userloginDTO.getEmail());
			
			
			if(userInLoginTable != null) {
				loginRepositoryInterface.deleteAll();
			}
			
			Login loginUser=new Login();
			loginUser.setEmail(userloginDTO.getEmail());
			
			Random r = new Random();
			
			String otpNumber = String.format("%06d", r.nextInt(1000000));
			loginUser.setOtp(otpNumber);
			
			Login userLogin = loginRepositoryInterface.save(loginUser);
			
			
			String subject="Email Login Verifaction";
			String body = "your Verifaction OTP is "+ userLogin.getOtp();
			
			emailService.sendMail(userLogin.getEmail(),subject , body);
			
			return "Login OTP sended successfully";
			
		}
		else {
			return "Not an Registered User !!!!";
		}
	}

		public String  LoginOtpVerifiy(UserLoginVerifiyDTO userLoginVerifiyDTO) {
			
				
					Login loginSuccess = loginRepositoryInterface.findByOtp(userLoginVerifiyDTO.getOtp());
					
					if(userLoginVerifiyDTO.getOtp() == null || userLoginVerifiyDTO.getOtp() == "") {
						loginRepositoryInterface.deleteAll();
						return "OTP Not Entered and Please try to Login Again";
					}
					
					if(loginSuccess != null) {
						loginRepositoryInterface.deleteAll();
						return "Login Success";
					}
					
					else {						
						return "OTP Is NOT CoRRect and Please recheck OTP";
					}
				
					
			
			
			
		}
}
