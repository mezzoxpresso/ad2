package com.movie.app.service;

import java.util.List;

import com.movie.app.model.MovieCategory;

public interface MovieCategoryService {

	public List<MovieCategory> findAllCategory();

	public MovieCategory createCategory(MovieCategory category);

	public void deleteCategory(MovieCategory category);

	public MovieCategory findCategoryById(int id);

	public MovieCategory editCategory(MovieCategory category);

}
