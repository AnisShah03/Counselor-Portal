package com.cf.CFProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cf.CFProject.Entities.Admin;


public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Optional<Admin> findByUsername(String username);
}
