package com.cf.CFProject.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cf.CFProject.Dto.FeedBackResponse;
import com.cf.CFProject.Dto.LoginRquest;
import com.cf.CFProject.Dto.ResponseDto;
import com.cf.CFProject.Entities.Admin;
import com.cf.CFProject.Entities.Users;
import com.cf.CFProject.Exception.AdminNotFoundException;
import com.cf.CFProject.Service.AdminService;
import com.cf.CFProject.repository.AdminRepository;
import com.cf.CFProject.repository.UserRepository;
import com.cf.CFProject.response.ResponseStructure;

import jakarta.transaction.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<?> getFeedBack(String username, int pageNumber) {

		Admin admin = adminRepository.findByUsername(username)
				.orElseThrow(() -> new AdminNotFoundException("admin is not found"));

//		List<Users> users = admin.getUsers();// for Counselor
//		if (users.isEmpty()) {
//			ResponseStructure<String> responseStructure = new ResponseStructure<>();
//			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
//			responseStructure.setMessage("No users found");
//			responseStructure.setData("user is not found in list");
//			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
//		}	

		Pageable request = PageRequest.of(pageNumber - 1, 5);

		List<Users> content = userRepository.findAll(request).getContent();

		ResponseStructure<List<Users>> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMessage("user list fetching");
		responseStructure.setData(content);
		return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);

	}

	@Override
	public ResponseEntity<?> updateFeedBack(FeedBackResponse response) {

		String username = response.getUsername();
		String targetName = response.getTargetName();
		String feedBack = response.getFeedBack();

		Admin admin = adminRepository.findByUsername(username)
				.orElseThrow(() -> new AdminNotFoundException("admin is not found"));

//		List<Users> users = admin.getUsers();
//		if (users.isEmpty()) {
//			ResponseStructure<String> responseStructure = new ResponseStructure<>();
//			responseStructure.setStatus(HttpStatus.NO_CONTENT.value());
//			responseStructure.setMessage("No users found");
//			return new ResponseEntity<>(responseStructure, HttpStatus.NO_CONTENT);
//		}

//		if (users.contains(targetName)) {
		Users user = userRepository.findByUsername(targetName).get();
		user.setFeedBack(feedBack);
		userRepository.save(user);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMessage("user list fetching");
		responseStructure.setData("feedback is updated");
		return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);

//		}

//		ResponseStructure<String> responseStructure = new ResponseStructure<>();
//		responseStructure.setStatus(HttpStatus.NO_CONTENT.value());
//		responseStructure.setMessage("feedback is not found in list");
//		return new ResponseEntity<>(responseStructure, HttpStatus.NO_CONTENT);

	}

	@Override
	public ResponseEntity<?> deleteFeedBack(String username, String targetName) {
		Admin admin = adminRepository.findByUsername(username)
				.orElseThrow(() -> new AdminNotFoundException("admin is not found"));

		Optional<Users> first = admin.getUsers().stream().filter(u -> u.getUsername().equals(targetName)).findFirst();

		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		if (first.isPresent()) {
			userRepository.deleteById(first.get().getId());
			responseStructure.setStatus(HttpStatus.ACCEPTED.value());
			responseStructure.setMessage("user list fetching");
			responseStructure.setData(targetName + " has been removed");
			return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);
		}
		responseStructure.setStatus(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage("User not found in the admin's list\"");
		return new ResponseEntity<>(responseStructure, HttpStatus.NO_CONTENT);

	}

	@Transactional
	@Override
	public ResponseEntity<?> registerAdmin(Admin admin) {
		Optional<Admin> adminOpt = adminRepository.findByUsername(admin.getUsername());
		if (adminOpt.isPresent()) {
			ResponseStructure<String> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("admin is already present");
			responseStructure.setData(admin.getEmail() + " is already present");
			return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);
		}
		adminRepository.save(admin);

//		ObjectMapper mapper = new ObjectMapper();
//		ResponseDto responseDto = mapper.convertValue(admin, ResponseDto.class);

		ResponseDto responseDto = new ResponseDto();
		BeanUtils.copyProperties(admin, responseDto);

		ResponseStructure<ResponseDto> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("admin is registered");
		responseStructure.setData(responseDto);
		return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<?> loginAdmin(LoginRquest loginRquest) {

		Admin admin = adminRepository.findByUsername(loginRquest.getUsername())
				.orElseThrow(() -> new AdminNotFoundException("admin is not found"));

		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		if (admin.getPassword().equals(loginRquest.getPassword())) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("admin is loggedIn");
			return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);
		}
		responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage("invalid password");
		return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);

	}

}
