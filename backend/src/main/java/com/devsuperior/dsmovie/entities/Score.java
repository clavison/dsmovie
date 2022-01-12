package com.devsuperior.dsmovie.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_score")
public class Score {

	@EmbeddedId
	private ScorePk id;
	private Double value;
	
	
	public Score() {
		id = new ScorePk();
	}

	public Score(ScorePk id, Double value) {
		this.id = id;
		this.value = value;
	}

	public ScorePk getId() {
		return id;
	}

	public void setId(ScorePk id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	public void setMovie(Movie movie) {
		this.id.setMovie(movie);
	}
	
	public void setUser(User user) {
		this.id.setUser(user);
	}
	
	
}
