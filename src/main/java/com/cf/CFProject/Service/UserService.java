package com.cf.CFProject.Service;

import org.springframework.http.ResponseEntity;

import com.cf.CFProject.Dto.FeedBackResponse;
import com.cf.CFProject.Dto.LoginRquest;
import com.cf.CFProject.Entities.Users;

public interface UserService {

	ResponseEntity<?> addFeedback(FeedBackResponse response);

	ResponseEntity<?> updateFeedback(FeedBackResponse response);

	ResponseEntity<?> registerUser(Users user);

	ResponseEntity<?> loginUser(LoginRquest loginRquest);

}
