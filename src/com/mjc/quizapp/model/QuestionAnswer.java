package com.mjc.quizapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "QuestionAnswer")
public class QuestionAnswer {

	@Id
	@Column(name="idQuestionAnswer")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idQuestionAnswer;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@OneToOne
	@JoinColumn(name="questionId")
	private Question question;
	
	@OneToOne
	@JoinColumn(name="choiceid")
	private QuestionChoice choice;
	
	@Column(name="isRightAnswer")
	private boolean isRightAnswer;
	
	@ManyToOne
	@JoinColumn(name="examid")
	private Exam exam;

	public int getIdQuestionAnswer() {
		return idQuestionAnswer;
	}

	public void setIdQuestionAnswer(int idQuestionAnswer) {
		this.idQuestionAnswer = idQuestionAnswer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public QuestionChoice getChoice() {
		return choice;
	}

	public void setChoice(QuestionChoice choice) {
		this.choice = choice;
	}

	public boolean isRightAnswer() {
		return isRightAnswer;
	}

	public void setRightAnswer(boolean isRightAnswer) {
		this.isRightAnswer = isRightAnswer;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}
	
}
