package com.movie.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.movie.app.model.Movie;

@Component
public class MovieValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Movie.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println(target);
		
//		ValidationUtils.rejectIfEmpty(errors, "movieTitle", "The Movie Title field cannot be empty.");
//	    ValidationUtils.rejectIfEmpty(errors, "movieDescription", "The Movie Description field cannot be empty.");
//	    ValidationUtils.rejectIfEmpty(errors, "actor1_name", "The Actor1 Name field cannot be empty.");
//	    ValidationUtils.rejectIfEmpty(errors, "actor2_name", "The Actor2 Name field cannot be empty.");
//	    ValidationUtils.rejectIfEmpty(errors, "content_rating", "The Content Rating field cannot be empty.");
//	    ValidationUtils.rejectIfEmpty(errors, "director", "The Director field cannot be empty.");
//	    ValidationUtils.rejectIfEmpty(errors, "movieReleaseDate", "The Movie Release Date field cannot be empty.");
	}
	
}
