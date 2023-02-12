package com.movie.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.movie.app.model.Review;

public interface ReviewRepository extends JpaRepository<Review,Integer>{
	
	
	@Query("Select r FROM Review r WHERE r.movie.movieId =:mid")
	public List<Review> getReviewByMID(@Param("mid") int mid);
	
	@Query("SELECT r FROM Review r WHERE r.movie.movieId=:mid AND r.user.userId=:uid")
	public Review getReviewByUIdAndMId(@Param("mid")int mid,@Param("uid")int uid);
	
	@Query("SELECT r FROM Review r WHERE r.user.userId=:uid")
	public List<Review> findAllByUserId(@Param("uid")int uid);
}
