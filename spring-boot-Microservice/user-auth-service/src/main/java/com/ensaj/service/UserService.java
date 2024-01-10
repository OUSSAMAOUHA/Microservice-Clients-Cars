package com.ensaj.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import com.ensaj.dto.LocalUser;
import com.ensaj.dto.SignUpRequest;
import com.ensaj.exception.UserAlreadyExistAuthenticationException;
import com.ensaj.model.User;

/**
 * @author Chinna
 * @since 26/3/18
 */
public interface UserService {

	public User registerNewUser(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

	User findUserByEmail(String email);

	Optional<User> findUserById(Long id);

	LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo);
	
	void createVerificationTokenForUser(User user, String token);

	boolean resendVerificationToken(String token);

	String validateVerificationToken(String token);
}
