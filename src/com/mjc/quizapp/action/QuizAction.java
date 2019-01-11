package com.mjc.quizapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.mjc.quizapp.model.Question;
import com.mjc.quizapp.model.User;
import com.mjc.quizapp.service.QuizzerService;
import com.mjc.quizapp.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class QuizAction extends ActionSupport implements ModelDriven<User>,
		SessionAware {

	private static final long serialVersionUID = 1L;
	private UserService userService;
	private QuizzerService quizzerService;
	List<Question> questions = new ArrayList<Question>();
	Map<String, Object> session;
	private User user = new User();

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String execute() throws Exception {

		userService.saveUser(user);
		session.put("user", user);

		// get the questions for quiz based on the difficulty level and the no of questions
		questions = quizzerService.getQuestionsForQuiz(Integer.parseInt(user.getDifficultyLevel()),
				Integer.parseInt(user.getNoOfQuestions()));

		return "quizpage";
	}

	public QuizzerService getQuizzerService() {
		return quizzerService;
	}

	public void setQuizzerService(QuizzerService quizzerService) {
		this.quizzerService = quizzerService;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

	@Override
	public User getModel() {
		return user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}
}
