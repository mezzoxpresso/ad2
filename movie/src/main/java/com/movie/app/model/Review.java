package com.movie.app.model;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int reviewId;
	
	private String comment;
	
	private double rating;
	
	@ManyToOne
	private Movie movie;
	
	@ManyToOne
	private User user;
	
	public Review() {
		
	}

	public Review(String comment, double rating, Movie movie, User user) {
		super();
		this.comment = comment;
		this.rating = rating;
		this.movie = movie;
		this.user = user;
	}



	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
