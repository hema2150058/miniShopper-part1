package com.mini.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini.model.User;

public interface UserRepo extends JpaRepository<User, String>{


	boolean existsByUsername(String email);

	User findByUserEmail(String email);
}
