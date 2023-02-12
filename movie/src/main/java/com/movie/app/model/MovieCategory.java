package com.movie.app.model;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MovieCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieCategoryId;
	
	private String movieCategoryName;
	
	public MovieCategory() {
		
	}
	
	public MovieCategory(String movieCategoryName) {
		super();
		this.movieCategoryName = movieCategoryName;
	}
	

	public int getMovieCategoryId() {
		return movieCategoryId;
	}

	public void setMovieCategoryId(int movieCategoryId) {
		this.movieCategoryId = movieCategoryId;
	}

	public String getMovieCategoryName() {
		return movieCategoryName;
	}

	public void setMovieCategoryName(String movieCategoryName) {
		this.movieCategoryName = movieCategoryName;
	}

	
	
}
