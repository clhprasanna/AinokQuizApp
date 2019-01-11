package com.mjc.quizapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Exam")
public class Exam {

	@Id
	@Column(name = "idExam" )
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idExam;
	
	@OneToOne
	@JoinColumn(name="userid")
	private User user;

	public int getIdExam() {
		return idExam;
	}

	public void setIdExam(int idExam) {
		this.idExam = idExam;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
