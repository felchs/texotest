package com.test.prj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movies")
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="year")
	private long year;
	
	@Column(name="title")
	private String title;
	
	@Column(name="studio")
	private String studio;
	
	@Column(name="producer")
	private String producer;
	
	@Column(name="winner")
	private String winner;
	
	public Movie() {
	}
	
	public Movie(long year, String title, String studio, String producer, String winner) {
		super();
		this.year = year;
		this.title = title;
		this.studio = studio;
		this.producer = producer;
		this.winner = winner;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getYear() {
		return year;
	}
	
	public void setYear(long year) {
		this.year = year;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStudio() {
		return studio;
	}
	
	public void setStudio(String studio) {
		this.studio = studio;
	}
	
	public String getProducer() {
		return producer;
	}
	
	public void setProducer(String producer) {
		this.producer = producer;
	}
	
	public String getWinner() {
		return winner;
	}
	
	public void setWinner(String winner) {
		this.winner = winner;
	}	
}
