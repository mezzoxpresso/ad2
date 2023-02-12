package com.movie.app.model;

public class FormReview {

	private int movieId;
	private String comment;
	private String editComment;
	
	public String getEditComment() {
		return editComment;
	}
	public void setEditComment(String editComment) {
		this.editComment = editComment;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
