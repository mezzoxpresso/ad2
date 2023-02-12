package com.movie.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int movieId;
	
	private String moviePoster;
	
	@NotBlank(message="The Movie Title field cannot be empty.")
	private String movieTitle;
	
	@NotBlank(message="The Movie Description field cannot be empty.")
	private String movieDescription;
	
	@NotBlank(message="The Actor1 Name field cannot be empty.")
	private String actor1_name;
	
	@NotBlank(message="The Actor2 Name field cannot be empty.")
	private String actor2_name;
	
	@NotBlank(message="The Content Rating field cannot be empty.")
	private String content_rating;
	
	@NotBlank(message="The Director field cannot be empty.")
	private String director;
	
	@NotBlank(message="The Movie Release Date field cannot be empty.")
	private String movieReleaseDate;
	
	private String movieCategory;
	
	@OneToMany(mappedBy="movie",cascade = CascadeType.ALL)
	private List<Review> reviews;
	
	public Movie() {
		
	}
	
	public Movie(String moviePoster, String movieTitle, String movieDescription, String actor1_name, String actor2_name,
			String content_rating, String director, String movieReleaseDate) {
		super();
		this.moviePoster = moviePoster;
		this.movieTitle = movieTitle;
		this.movieDescription = movieDescription;
		this.actor1_name = actor1_name;
		this.actor2_name = actor2_name;
		this.content_rating = content_rating;
		this.director = director;
		this.movieReleaseDate = movieReleaseDate;
	}


	public Movie(String moviePoster, String movieTitle, String movieDescription, String actor1_name, String actor2_name,
			String content_rating, String director, String movieReleaseDate, String movieCategory) {
		super();
		this.moviePoster = moviePoster;
		this.movieTitle = movieTitle;
		this.movieDescription = movieDescription;
		this.actor1_name = actor1_name;
		this.actor2_name = actor2_name;
		this.content_rating = content_rating;
		this.director = director;
		this.movieReleaseDate = movieReleaseDate;
		this.movieCategory = movieCategory;
	}


	public Movie(String moviePoster, String movieTitle, String movieDescription, String actor1_name,
			String actor2_name, String content_rating, String director, String movieReleaseDate,
			String movieCategory, List<Review> reviews) {
		super();
		this.moviePoster = moviePoster;
		this.movieTitle = movieTitle;
		this.movieDescription = movieDescription;
		this.actor1_name = actor1_name;
		this.actor2_name = actor2_name;
		this.content_rating = content_rating;
		this.director = director;
		this.movieReleaseDate = movieReleaseDate;
		this.movieCategory = movieCategory;
		this.reviews = reviews;
	}
	

	public String getActor1_name() {
		return actor1_name;
	}

	public void setActor1_name(String actor1_name) {
		this.actor1_name = actor1_name;
	}

	public String getActor2_name() {
		return actor2_name;
	}

	public void setActor2_name(String actor2_name) {
		this.actor2_name = actor2_name;
	}

	public String getContent_rating() {
		return content_rating;
	}
	
	public void setContent_rating(String content_rating) {
		this.content_rating = content_rating;
	}

	public String getMovieReleaseDate() {
		return movieReleaseDate;
	}

	public void setMovieReleaseDate(String movieReleaseDate) {
		this.movieReleaseDate = movieReleaseDate;
	}


	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMoviePoster() {
		return moviePoster;
	}

	public void setMoviePoster(String moviePoster) {
		this.moviePoster = moviePoster;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getMovieDescription() {
		return movieDescription;
	}

	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}


	public String getMovieCategory() {
		return movieCategory;
	}

	public void setMovieCategory(String movieCategory) {
		this.movieCategory = movieCategory;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}


}