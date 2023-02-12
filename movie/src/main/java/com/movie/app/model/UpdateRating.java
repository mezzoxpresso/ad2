package com.movie.app.model;

public class UpdateRating {

	private double ratingValue;
	private int movieId;
	
	public Double getRatingValue() {
		return ratingValue;
	}
	public void setRatingValue(double ratingValue) {
		this.ratingValue = ratingValue;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
}
