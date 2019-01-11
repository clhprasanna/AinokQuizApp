package com.mjc.quizapp.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mjc.quizapp.model.User;

@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveUser(User user) {

		try
		{

			sessionFactory.getCurrentSession().saveOrUpdate(user);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		
	}

}
