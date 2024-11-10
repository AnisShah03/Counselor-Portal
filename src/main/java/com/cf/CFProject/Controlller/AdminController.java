package com.cf.CFProject.Controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cf.CFProject.Dto.FeedBackResponse;
import com.cf.CFProject.Service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/{username}/{pageNumber}")
	public ResponseEntity<?> getFeedBack(@PathVariable String username, @PathVariable int pageNumber) {
		return adminService.getFeedBack(username, pageNumber);
	}

	@PatchMapping("/update")
	public ResponseEntity<?> updateFeedBack(@RequestBody FeedBackResponse response) {
		return adminService.updateFeedBack(response);
	}

	@DeleteMapping("/d/{username}/{targetName}")
	public ResponseEntity<?> deleteFeedBack(@PathVariable String username,@PathVariable String targetName) {
		return adminService.deleteFeedBack(username, targetName);
	}

}
