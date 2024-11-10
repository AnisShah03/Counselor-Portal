package com.cf.CFProject.Controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cf.CFProject.Dto.FeedBackResponse;
import com.cf.CFProject.Service.UserService;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/add")
	public ResponseEntity<?> addFeedback(@RequestBody FeedBackResponse response) {
		return userService.addFeedback(response);
	}

	@PatchMapping("/update")
	public ResponseEntity<?> updateFeedback(@RequestBody FeedBackResponse response) {
		return userService.updateFeedback(response);
	}

}
