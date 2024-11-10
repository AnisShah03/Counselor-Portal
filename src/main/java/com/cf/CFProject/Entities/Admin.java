package com.cf.CFProject.Entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity

@Getter
@Setter
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String username;

	@Email(message = "email is required")
	private String email;

	@Size(min = 3, max = 10, message = "provide valid password")
	private String password;

	private Long phone;

//	@Enumerated(EnumType.STRING)
//	private Mode mode=Mode.ADMIN;

	@OneToMany(mappedBy = "admin")
	private List<Users> users;

}
