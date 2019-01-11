package com.mjc.quizapp.action;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import com.mjc.quizapp.model.User;
import com.mjc.quizapp.service.QuizzerService;
import com.opensymphony.xwork2.ActionSupport;

public class Evaluation extends ActionSupport  implements ParameterAware, SessionAware{	
	
	private static final long serialVersionUID = 1L;
	Map<String,String[]> choiceMap;
	List<String> choices; 
	private QuizzerService quizzerService;
	Map<String, Object> session;

	public List<String> getChoices() {
		return choices;
	}


	public void setChoices(List<String> choices) {
		this.choices = choices;
	}

	public String execute() throws Exception {
		
		List<Integer> choiceIds = new ArrayList<Integer>();
		
		// get the answers selected by the user for each of the questions
		for(Map.Entry<String, String[]> entry: choiceMap.entrySet())
		{
			for(String value : entry.getValue())
			{
						choiceIds.add(Integer.parseInt(value));
			}
		}
		
		User user = (User)session.get("user");
		float result = quizzerService.SaveAndEvaluateExam(user,choiceIds);
		session.put("percentage", result);
				
		return "results";
		
		}


	@Override
	public void setParameters(Map<String, String[]> map) {
		choiceMap = map;
	}


	public QuizzerService getQuizzerService() {
		return quizzerService;
	}


	public void setQuizzerService(QuizzerService quizzerService) {
		this.quizzerService = quizzerService;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		session = sessionMap;
		
	}
}
