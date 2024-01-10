package com.ensaj.service;

import com.ensaj.model.User;

public interface MailService {

	void sendVerificationToken(String token, User user);
}
