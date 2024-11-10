package com.cf.CFProject.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	private String username;

	@Email(message = "email is required")
	private String email;

	@Size(min = 3, max = 10, message = "provide valid password")
	private String password;

	@Digits(integer = 10, message = "give valid phone number", fraction = 0)
	private Long phone;

//	@Enumerated(EnumType.STRING)
//	private Mode mode = Mode.USER;

	@Column(nullable = true)
	private String feedBack;

	@ManyToOne
	@JoinColumn(name = "admin_name")
	@JsonIgnore
	private Admin admin;

}
