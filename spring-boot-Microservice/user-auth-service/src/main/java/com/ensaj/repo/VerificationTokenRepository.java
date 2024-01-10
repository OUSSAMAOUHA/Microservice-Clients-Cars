package com.ensaj.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensaj.model.User;
import com.ensaj.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	VerificationToken findByToken(String token);

	VerificationToken findByUser(User user);
}
