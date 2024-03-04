package com.otp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.otp.entity.User;
import com.otp.payload.UserLoginVerifiyDTO;
import com.otp.payload.UserRequestDTO;
import com.otp.payload.UserResponseDTO;
import com.otp.payload.UserVerifiyDTO;
import com.otp.payload.UserloginDTO;
import com.otp.service.LoginService;
import com.otp.service.UserService;

@RestController
public class UserLoginController {
	
	@Autowired
	LoginService loginService;
	
	@PostMapping("/user-login")
	public ResponseEntity<?> userLogin(@RequestBody  UserloginDTO userloginDTO){
		
		String login = loginService.LoginVerifiy(userloginDTO);
		
		return new ResponseEntity<String>(login,HttpStatus.OK);
		
	}
	
	@PostMapping("/user-login-verifiy")
	public ResponseEntity<?> userLoginVerifiy(@RequestBody  UserLoginVerifiyDTO userLoginVerifiyDTO){
		
			String loginOtp = loginService.LoginOtpVerifiy(userLoginVerifiyDTO);
		
			return new ResponseEntity<String>(loginOtp,HttpStatus.OK);
		
	}
}
