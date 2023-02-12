package com.movie.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.app.model.MovieCategory;

public interface MovieCategoryRepository extends JpaRepository<MovieCategory,Integer>{

}
