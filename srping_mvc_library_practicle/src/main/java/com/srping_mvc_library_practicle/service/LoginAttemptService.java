package com.srping_mvc_library_practicle.service;

public interface LoginAttemptService {
	 void loginSucceeded(String username);
	 void loginFailed(String username);
}
