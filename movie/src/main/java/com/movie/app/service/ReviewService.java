package com.movie.app.service;

import java.util.List;

import com.movie.app.model.Review;

public interface ReviewService {
	
	public List<Review> findAll();
	
	public List<Review> findAllById(int uid);
	
	public List<Review> getReviewByMovieId(int mid);
	
	public Review createNewReview(Review review);
	
	public Review getReviewByUserIdAndMovieId(int mid,int uid);
	
	public Review updateReview(Review review);
	
	public Review getReviewById(int rid);
	
	public void deleteReview(Review review);
}
