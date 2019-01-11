package com.mjc.quizapp.service;

import org.springframework.stereotype.Service;

import com.mjc.quizapp.dao.UserDAO;
import com.mjc.quizapp.model.User;

@Service
public class UserServiceImpl implements UserService {

private UserDAO userDAO;
	
	
	public void saveUser(User user) {

		userDAO.saveUser(user);
		
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	
	
}
