package com.mjc.quizapp.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mjc.quizapp.model.Exam;
import com.mjc.quizapp.model.Question;
import com.mjc.quizapp.model.QuestionAnswer;
import com.mjc.quizapp.model.QuestionChoice;

@Transactional
public class QuizzerDAOImpl implements QuizzerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Question> getQuestionsForQuiz(int difficultyLevel, int nQuestions) {

		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Question q where difficultyLevel =:difficultyLevel order by q.count");
		query.setParameter("difficultyLevel", difficultyLevel);
		query.setMaxResults(nQuestions);
		List<Question> questions = query.list();

		return questions;
	}

	@Override
	public List<QuestionChoice> getQuestionChoicesById(List<Integer> choiceIds) 
	{

		List<QuestionChoice> questionChoiceList = null;
		
		try
		{
			Query query = sessionFactory.getCurrentSession().createQuery(
				"from QuestionChoice where idChoice in:choiceIds");
			query.setParameterList("choiceIds", choiceIds);

			questionChoiceList = query.list();
		}
		catch(HibernateException he)
		{
			he.printStackTrace();
		}
		return questionChoiceList;
	}

	@Override
	public void saveAllQuestionAnswers(List<QuestionAnswer> questionAnswers) 
	{

		try
		{
			Session session = sessionFactory.getCurrentSession();
			
			for(int i=0; i<questionAnswers.size(); i++)
			{
				session.save(questionAnswers.get(i));
				
				if(i%20 == 0)
				{
					session.flush();
					session.clear();
				}
			}

		}
		catch(HibernateException he)
		{
			he.printStackTrace();
		}
		
		
	}
	
	public void updateQuestionCount(List<Integer> questionIds)
	{
		String hql = "Update Question q set q.count=q.count+1 where q.idQuestion in:questionids";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameterList("questionids", questionIds);
		
		query.executeUpdate();
	}
	
	public void saveExam(Exam exam)
	{
		sessionFactory.getCurrentSession().save(exam);
	}

	@Override
	public void recalculateDifficultyLevelByQuestionId(List<Integer> questionIds) {

		Session session = sessionFactory.getCurrentSession();
		String qPart1 = "select count(*) from (select isRightAnswer from QuestionAnswer where questionId = ";
		String qPart2 = " order by idQuestionAnswer limit 10) x where x.isRightAnswer = 1";
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append(qPart1);
		Map<Integer,Integer> dMap = new HashMap<Integer,Integer>();
		int nOfCorrectAnswers = 0;
		int newDidifficultyLevel = 0;
		String uQuery1 = "Update Question set difficultyLevel = ";
		String uQuery2 = " where idQuestion =";
		StringBuilder updateQuery = new StringBuilder();
		
		for(Integer questionId : questionIds)
		{
			
			sqlQuery.append(questionId);
			sqlQuery.append(qPart2);
			
			Query query = session.createSQLQuery(sqlQuery.toString());
			nOfCorrectAnswers = ((BigInteger)query.uniqueResult()).intValue();
			
			newDidifficultyLevel = (9 - nOfCorrectAnswers);
			dMap.put(questionId, newDidifficultyLevel);
			
			sqlQuery.delete(qPart1.length(), sqlQuery.length());
			
			//
			updateQuery.append(uQuery1);
			updateQuery.append(newDidifficultyLevel+uQuery2+questionId);
			
			query = session.createSQLQuery(updateQuery.toString());
			query.executeUpdate();
			updateQuery.delete(0, updateQuery.length());
		}
		
	}

}
