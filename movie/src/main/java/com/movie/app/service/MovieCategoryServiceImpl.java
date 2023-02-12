package com.movie.app.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.app.model.MovieCategory;
import com.movie.app.repository.MovieCategoryRepository;

import javax.transaction.Transactional;

@Service
public class MovieCategoryServiceImpl implements MovieCategoryService {

	@Autowired
	private MovieCategoryRepository movieCategoryRepo;

	// findAll
	@Override
	@Transactional
	public List<MovieCategory> findAllCategory() {
		return movieCategoryRepo.findAll();
	}

	// create
	@Override
	@Transactional
	public MovieCategory createCategory(MovieCategory category) {
		return movieCategoryRepo.save(category);
	}

	// delete
	@Override
	@Transactional
	public void deleteCategory(MovieCategory category) {
		movieCategoryRepo.delete(category);

	}

	// findById
	@Override
	@Transactional
	public MovieCategory findCategoryById(int id) {
		return movieCategoryRepo.findById(id).get();
	}

	// edit
	@Override
	@Transactional
	public MovieCategory editCategory(MovieCategory category) {
		return movieCategoryRepo.saveAndFlush(category);
	}
}
