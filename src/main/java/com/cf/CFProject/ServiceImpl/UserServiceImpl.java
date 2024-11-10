package com.cf.CFProject.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cf.CFProject.Dto.FeedBackResponse;
import com.cf.CFProject.Dto.LoginRquest;
import com.cf.CFProject.Dto.ResponseDto;
import com.cf.CFProject.Entities.Users;
import com.cf.CFProject.Exception.UserNotFoundException;
import com.cf.CFProject.Service.UserService;
import com.cf.CFProject.repository.UserRepository;
import com.cf.CFProject.response.ResponseStructure;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<?> addFeedback(FeedBackResponse response) {

		String username = response.getUsername();
		String feedBack = response.getFeedBack();

		Users user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("user is not found"));
		user.setFeedBack(feedBack);
		userRepository.save(user);

		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("user uploaded feedback");
		responseStructure.setData(username+"feedback is added");
		return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<?> updateFeedback(FeedBackResponse response) {
		String username = response.getUsername();
		String feedBack = response.getFeedBack();

		Users user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("user is not found"));
		user.setFeedBack(feedBack);
		userRepository.save(user);

		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.ACCEPTED.value());
		responseStructure.setMessage("user uploaded feedback");
		return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);

	}

	@Override
	public ResponseEntity<?> registerUser(Users user) {
		Optional<Users> userOpt = userRepository.findByUsername(user.getUsername());
		if (userOpt.isPresent()) {
			ResponseStructure<String> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("user is already present");
			responseStructure.setData(user.getEmail() + " is already present");
			return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);
		}
		userRepository.save(user);

		ResponseDto responseDto = new ResponseDto();
		BeanUtils.copyProperties(user, responseDto);

		ResponseStructure<ResponseDto> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("user is registered");
		responseStructure.setData(responseDto);
		return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<?> loginUser(LoginRquest loginRquest) {

		Users user = userRepository.findByUsername(loginRquest.getUsername())
				.orElseThrow(() -> new UserNotFoundException("user is not found"));

		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		if (user.getPassword().equals(loginRquest.getPassword())) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("user is loggedIn");
			return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);
		}
		responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage("invalid password");
		return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);

	}

}
