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
import com.otp.payload.UserRequestDTO;
import com.otp.payload.UserResponseDTO;
import com.otp.payload.UserVerifiyDTO;
import com.otp.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/user-register")
	public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO){
		
		UserResponseDTO dto = userService.registerUser(userRequestDTO);
		
		return new ResponseEntity<UserResponseDTO>(dto,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/user-verifiy")
	public ResponseEntity<?> verifiyUser(@RequestBody UserVerifiyDTO userVerifiyDTO){
		
		String str = userService.verifyUser(userVerifiyDTO.getEmail(),userVerifiyDTO.getOtp());
		
		return new ResponseEntity<String>(str,HttpStatus.OK);
		
	}
}
