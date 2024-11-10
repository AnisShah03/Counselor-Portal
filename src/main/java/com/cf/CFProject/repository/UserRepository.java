package com.cf.CFProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cf.CFProject.Entities.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

	Optional<Users> findByUsername(String username);

}