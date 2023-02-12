package com.movie.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.app.model.Review;
import com.movie.app.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewRepository reviewRepo;
	
	//findById
	@Override
	public List<Review> getReviewByMovieId(int mid) {
		List<Review> review=reviewRepo.getReviewByMID(mid);
		return review;
	}
	
	//create
	@Override
	public Review createNewReview(Review review) {	
		return reviewRepo.save(review);
	}

	@Override
	public Review getReviewByUserIdAndMovieId(int mid, int uid) {
		return reviewRepo.getReviewByUIdAndMId(mid, uid);
	}

	@Override
	public Review updateReview(Review review) {
		return reviewRepo.saveAndFlush(review);
	}

	@Override
	public Review getReviewById(int rid) {
		return reviewRepo.findById(rid).get();
	}

	@Override
	public void deleteReview(Review review) {
		reviewRepo.delete(review);
		
	}

	@Override
	public List<Review> findAll() {
		
		return reviewRepo.findAll();
	}

	@Override
	public List<Review> findAllById(int uid) {

		return reviewRepo.findAllByUserId(uid);
	}
	
	
	
}
