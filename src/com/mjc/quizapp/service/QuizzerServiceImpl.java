package com.mjc.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mjc.quizapp.dao.QuizzerDAO;
import com.mjc.quizapp.model.Exam;
import com.mjc.quizapp.model.Question;
import com.mjc.quizapp.model.QuestionAnswer;
import com.mjc.quizapp.model.QuestionChoice;
import com.mjc.quizapp.model.User;

@Service
public class QuizzerServiceImpl implements QuizzerService {
	
	@Autowired
	private QuizzerDAO quizzerDAO;

	@Override
	public List<Question> getQuestionsForQuiz(int difficultyLevel, int nQuestions) {
		
		return quizzerDAO.getQuestionsForQuiz(difficultyLevel,nQuestions);
	}

	@Transactional
	public float SaveAndEvaluateExam(User user, List<Integer> answerIDList) {
		
		// save the exam
		
		Exam exam = new Exam();
		exam.setUser(user);
		quizzerDAO.saveExam(exam);
		
		// get a list of questionChoice objects - use criteria to get a collection of objects
		List<QuestionChoice> questionChoiceList = quizzerDAO.getQuestionChoicesById(answerIDList);
		
		// construct collection of QuestionAnswer objects and persists the same to DB
	
		int nOfCorrectAnswers = 0;
		List<Integer> questionIdList = new ArrayList<Integer>();
		List<Integer> questionIdsForReCal = new ArrayList<Integer>(); 
		List<QuestionAnswer> questionAnswerList = new ArrayList<QuestionAnswer>();
		for(QuestionChoice choice : questionChoiceList)
		{
			QuestionAnswer questionAnswer = new QuestionAnswer();
			questionAnswer.setChoice(choice);
			questionAnswer.setQuestion(choice.getQuestion());
			questionAnswer.setRightAnswer(choice.isRightChoice());
			questionAnswer.setUser(user);
			questionAnswer.setExam(exam);
			questionAnswerList.add(questionAnswer);
			
			if(choice.isRightChoice())
				nOfCorrectAnswers++;
			
			if(choice.getQuestion().getIdQuestion() % 10 == 0)
				questionIdsForReCal.add(choice.getQuestion().getIdQuestion());
			
			questionIdList.add(choice.getQuestion().getIdQuestion());
		}
		
		quizzerDAO.saveAllQuestionAnswers(questionAnswerList);
		
		
		// update the question count
		quizzerDAO.updateQuestionCount(questionIdList);
		
		// Evaluate the quiz and return the results back to the user
		// check the number of correct answers and calculate the percentage
		
		float percentage = ((float)nOfCorrectAnswers/answerIDList.size())*100;
		
		// recalculate the difficulty level of the questions. difficulty levels are recalculated for every 10 attempts of the question
		
		if(questionIdsForReCal.size() > 0)
			quizzerDAO.recalculateDifficultyLevelByQuestionId(questionIdsForReCal);
		
		return percentage;
		
	}

	
}
