package com.movie.app.service;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.movie.app.model.Movie;
import com.movie.app.model.MovieCategory;
import com.movie.app.repository.MovieRepository;

import javax.transaction.Transactional;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepo;

	// findAll
	@Override
	@Transactional
	public List<Movie> findAllMovies() {
		return movieRepo.findAll();
	}

	// create
	@Override
	@Transactional
	public void createMovie(MultipartFile file, Movie movie, List<MovieCategory> categories) {

		//
		String fileName = file.getOriginalFilename();

		// Random name generate
		String randomID = UUID.randomUUID().toString();
		String fileName1 = randomID.concat(fileName.substring(fileName.lastIndexOf(".")));

		if (fileName1.contains("..")) {
			System.out.println("not a valid file");
		}

		movie.setMoviePoster(fileName1);

//		if (movie.getActor1_name() == null) {
//			movie.setActor1_name("");
//		}
//
//		if (movie.getActor2_name() == null) {
//			movie.setActor2_name("");
//		}
//
//		if (movie.getDirector() == null) {
//			movie.setDirector("");
//		}
//
//		if (movie.getMovieReleaseDate() == null) {
//			movie.setMovieReleaseDate("");
//		}
//
//		if (movie.getContent_rating() == null) {
//			movie.setContent_rating("");
//		}
//
//		if (movie.getMovieDescription() == null) {
//			movie.setMovieDescription("");
//		}

		String category = "";

		for (MovieCategory c : categories) {
			category += c.getMovieCategoryName() + "|";
		}

		movie.setMovieCategory(category);

		movieRepo.save(movie);

		try {
			File saveFile = new ClassPathResource("static/images").getFile();
			Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + fileName1);
			System.out.println(path);
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}

	// findById
	@Override
	public Movie findMovie(int id) {
		System.out.println("iddddd"+id);
		return movieRepo.findById(id).get();
	}

	// delete
	@Override
	@Transactional
	public void deleteMovie(Movie movie) {
		movieRepo.delete(movie);

	}

	// edit
	@Override
	@Transactional
	public Movie editMovie(MultipartFile file, Movie movie, List<MovieCategory> categories) {
		String fileName = file.getOriginalFilename();

		// Random name generate
		String randomID = UUID.randomUUID().toString();
		String fileName1 = randomID.concat(fileName.substring(fileName.lastIndexOf(".")));

		if (fileName.contains("..")) {
			System.out.println("not a valid file");
		}

		movie.setMoviePoster(fileName1);

		String category = "";

		for (MovieCategory c : categories) {
			category += c.getMovieCategoryName() + "|";
		}

		movie.setMovieCategory(category);

		movie.setMovieCategory(category);

		Movie m = movieRepo.saveAndFlush(movie);

		try {
			File saveFile = new ClassPathResource("static/images").getFile();
			Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + fileName1);
			System.out.println(path);
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		return m;

	}

	// MovieDetails
	// MovieRating
	// getAverageRatingToDisplay
	@Override
	public double getTotalRatingByMovieId(int id) {
		//System.out.println("--------"+movieRepo.getTotalRating(id));
		double total = movieRepo.getTotalRating(id);
		int totalNumber = movieRepo.getTotalNumber(id);
		if(total != 0) {
			double avg = (total / totalNumber);
			return Math.round(avg*10.0)/10.0;
		}else {
			return 0.0;
		}
		
	}

}
