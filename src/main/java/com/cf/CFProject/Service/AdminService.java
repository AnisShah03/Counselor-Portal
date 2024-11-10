package com.cf.CFProject.Service;

import org.springframework.http.ResponseEntity;

import com.cf.CFProject.Dto.FeedBackResponse;
import com.cf.CFProject.Dto.LoginRquest;
import com.cf.CFProject.Entities.Admin;

public interface AdminService {


	ResponseEntity<?> registerAdmin(Admin admin);

	ResponseEntity<?> loginAdmin(LoginRquest loginRquest);

	ResponseEntity<?> getFeedBack(String username, int pageNumber);

	ResponseEntity<?> updateFeedBack(FeedBackResponse response);

	ResponseEntity<?> deleteFeedBack(String username, String targetName);


}
