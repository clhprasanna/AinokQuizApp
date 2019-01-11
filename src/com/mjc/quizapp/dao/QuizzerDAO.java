package com.mjc.quizapp.dao;

import java.util.List;

import com.mjc.quizapp.model.Exam;
import com.mjc.quizapp.model.Question;
import com.mjc.quizapp.model.QuestionAnswer;
import com.mjc.quizapp.model.QuestionChoice;

public interface QuizzerDAO {

	public List<Question> getQuestionsForQuiz(int difficultyLevel, int nQuestions);
	public List<QuestionChoice> getQuestionChoicesById(List<Integer> choiceIds);
	public void saveAllQuestionAnswers(List<QuestionAnswer> questionAnswers);
	public void updateQuestionCount(List<Integer> questionIds);
	public void saveExam(Exam exam);
	public void recalculateDifficultyLevelByQuestionId(List<Integer> questionIds);
	
}
