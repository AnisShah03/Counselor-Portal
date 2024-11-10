package com.cf.CFProject.Controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cf.CFProject.Dto.LoginRquest;
import com.cf.CFProject.Entities.Admin;
import com.cf.CFProject.Entities.Users;
import com.cf.CFProject.Service.AdminService;
import com.cf.CFProject.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;

	@PostMapping(value = "/a/register")
	public ResponseEntity<?> adminRegister(@Valid @RequestBody Admin admin) {
		return adminService.registerAdmin(admin);
	}

	@PostMapping("/a/login")
	public ResponseEntity<?> adminLogin(@RequestBody LoginRquest loginRquest) {
		return adminService.loginAdmin(loginRquest);
	}

	@PostMapping("/u/register")
	public ResponseEntity<?> userRegister(@RequestBody Users users) {
		return userService.registerUser(users);
	}

	@PostMapping("/u/login")
	public ResponseEntity<?> userLogin(@RequestBody LoginRquest loginRquest) {
		return userService.loginUser(loginRquest);
	}

}
