package com.mjc.quizapp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Question")
public class Question {
	
	@Id
	@Column(name = "idQuestion" )
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idQuestion;
	
	@Column(name="question")
	private String question;
	
	@Column(name="difficultylevel")
	private int difficultylevel;
	
	@Column(name="count")
	private int count;

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER,mappedBy="question", targetEntity=QuestionChoice.class)
	private List<QuestionChoice> choices;// = new ArrayList<QuestionChoice>(0);
	
	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getDifficultylevel() {
		return difficultylevel;
	}

	public void setDifficultylevel(int difficultylevel) {
		this.difficultylevel = difficultylevel;
	}

	
	public List<QuestionChoice> getChoices() {
		return choices;
	}

	public void setChoices(List<QuestionChoice> choices) {
		this.choices = choices;
	}

}
