package com.mjc.quizapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "QuestionChoice")
public class QuestionChoice {
	
	@Id
	@Column(name="idChoice")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idChoice;
	
	@Column(name="choice")
	private String choice;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "questionId")
	private Question question;
	
	@Column(name="isRightChoice")
	private boolean isRightChoice;
	
	public int getIdChoice() {
		return idChoice;
	}
	public void setIdChoice(int idChoice) {
		this.idChoice = idChoice;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public boolean isRightChoice() {
		return isRightChoice;
	}
	public void setRightChoice(boolean isRightChoice) {
		this.isRightChoice = isRightChoice;
	}

}
