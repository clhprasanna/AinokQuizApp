package com.mjc.quizapp.service;

import java.util.List;

import com.mjc.quizapp.model.Question;
import com.mjc.quizapp.model.User;

public interface QuizzerService {

	public List<Question> getQuestionsForQuiz(int difficultyLevel, int nQuestions); 
	
	public float SaveAndEvaluateExam(User user,List<Integer> answerIDList);
}
