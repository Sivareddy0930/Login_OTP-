package com.otp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.otp.entity.User;

@Repository
public interface UserRepositoryInterface extends JpaRepository<User,Long> {

	User findByEmail(String email);
	
}
