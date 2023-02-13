package com.movie.app.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.movie.app.model.FormReview;
import com.movie.app.model.Movie;
import com.movie.app.model.MovieCategory;
import com.movie.app.model.Review;
import com.movie.app.model.UpdateRating;
import com.movie.app.model.User;
import com.movie.app.model.UserSession;
import com.movie.app.service.MovieCategoryService;
import com.movie.app.service.MovieService;
import com.movie.app.service.ReviewService;
import com.movie.app.service.UserService;
import com.movie.app.validator.MovieValidator;
import javax.validation.Valid;

import javax.servlet.http.HttpSession;

@Controller
public class MovieController {

	@Autowired
	private MovieService movieService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private UserService userService;

	@Autowired
	private MovieCategoryService movieCategoryService;
	

	// displayCreateForm
	@GetMapping("/admin/createForm")
	public String createMovieForm(Model model) {

		// for displaying multiple check-boxes for genres
		List<MovieCategory> movieCategories = movieCategoryService.findAllCategory();
		model.addAttribute("movieCategories", movieCategories);
		model.addAttribute("movie", new Movie());

		return "movie_new";
	}

	// displayCreatedMovieList
	@GetMapping("/admin/movieList")
	public String getAllCreatedMovieList(Model model) {

		List<Movie> movieList = movieService.findAllMovies();
		model.addAttribute("movieList", movieList);

		return "movielist";
	}

	// createMovie
	@PostMapping("/admin/createMovie")
	public String createNewMovie(@ModelAttribute @Valid Movie movie,BindingResult result, @RequestParam("file") MultipartFile file,
			@RequestParam("categories") List<MovieCategory> categories,Model model) {
		
		if (result.hasErrors()) {
			List<MovieCategory> movieCategories = movieCategoryService.findAllCategory();
			model.addAttribute("movieCategories", movieCategories);
			return "movie_new";
		}
		
		movieService.createMovie(file, movie, categories);

		return "redirect:/admin/movieList";
	}

	// displayEditForm
	@GetMapping("/admin/movie/edit/{id}")
	public String editMovieForm(@PathVariable int id, Model model) {

		List<MovieCategory> movieCategories = movieCategoryService.findAllCategory();
		model.addAttribute("movieCategories", movieCategories);

		Movie movie = movieService.findMovie(id);
		model.addAttribute("movie", movie);

		// change movieCategory String type to the List
		String s = movie.getMovieCategory();
		String[] sArr = s.split("[|]");

		List<String> catString = new ArrayList<>();
		for (String a : sArr) {
			catString.add(a);
		}

		model.addAttribute("catString", catString);

		return "movie_edit";
	}

	// editMovie
	@PostMapping("/admin/movie/edit")
	public String editMovie(@ModelAttribute Movie movie,@RequestParam("file") MultipartFile file,
			@RequestParam("categories") List<MovieCategory> categories) {
		
		movieService.editMovie(file, movie, categories);

		return "redirect:/admin/movieList";
	}

	// deleteMovie
	@GetMapping("/admin/movie/delete/{id}")
	public String deleteMovie(@PathVariable int id) {

		Movie movie = movieService.findMovie(id);
		movieService.deleteMovie(movie);

		return "redirect:/admin/movieList";
	}
	// -------------------------------------------------------------------------
	// MovieDetails

	@GetMapping("/movieDetails") // id?(will get from main page)
	public String movieDetails(Model model, HttpSession session) {

		// get movie by id//will get from main page
		int mid = 2;

		// Get Movie Data
		Movie m = movieService.findMovie(mid);
		model.addAttribute("movie", m);

		FormReview fr = new FormReview();
		fr.setMovieId(m.getMovieId());
		model.addAttribute("formReview", fr);

		List<Review> reviewList = reviewService.getReviewByMovieId(mid);
		
		// Rating calculation
		double movieRating = 0;
		if (reviewList.size() > 0) {
			movieRating = movieService.getTotalRatingByMovieId(mid);
		}

		model.addAttribute("movieRating", movieRating);

		boolean isAdmin = false;

		// if no session let id =0(non-login user)
		int uid = -1;
		
		UserSession usession = (UserSession) session.getAttribute("usession");
		
		if (usession != null) {	
			User user = usession.getUser();
			uid = user.getUserId();
			List<String> roleNames = user.getRoleNames();

			if (roleNames.contains("Admin")) {
				isAdmin = true;
			}
			
		}

		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("loginUserId", uid);
		
		return "movie_details";
	}

	@SuppressWarnings("unused")
	@PostMapping("/review")
	public String createNewReview(@ModelAttribute FormReview fReview, HttpSession session) {
		
		String comment;

		if (!fReview.getComment().isEmpty() || !fReview.getComment().isBlank()) {
			System.out.println(fReview.getComment());
			comment = fReview.getComment();
		} else {
			System.out.println(fReview.getEditComment());
			comment = fReview.getEditComment();
		}

		int movieId = fReview.getMovieId();
		
		UserSession usession = (UserSession) session.getAttribute("usession");
		
		if (usession == null) {
			return "redirect:/login";
		}
		
		else 
		{			
			int uid = usession.getUser().getUserId();
			System.out.println(uid);
			
			User user = userService.findUserById(uid);

			Movie movie = movieService.findMovie(movieId);
			Review review1 = reviewService.getReviewByUserIdAndMovieId(movieId, uid);

			if (review1 == null) {
				Review review = new Review();
				review.setComment(comment);
				review.setUser(user);
				review.setMovie(movie);

				reviewService.createNewReview(review);
			}

			else {
				review1.setComment(comment);
				reviewService.updateReview(review1);
			}
			return "redirect:/movieDetails";
		}

	}

	@SuppressWarnings("unused")
	@PostMapping("/rating")
	public ResponseEntity<?> getSearchResultViaAjax(@RequestBody UpdateRating updateRating, Error errors,HttpSession session) 
	{

		double rating = 0.0;
		
		// getSession to check null or not
		UserSession usession = (UserSession) session.getAttribute("usession");
		
		if (usession == null) {
			return ResponseEntity.badRequest().body("/login");
		} 
		else 
		{
			int uid = usession.getUser().getUserId();
			System.out.println(uid);
			
			User user = userService.findUserById(uid);
			Movie movie = movieService.findMovie(updateRating.getMovieId());
			Review review1 = reviewService.getReviewByUserIdAndMovieId(updateRating.getMovieId(), uid);

			if (review1 == null) {
				Review review = new Review();
				review.setRating(updateRating.getRatingValue());
				review.setUser(user);
				review.setMovie(movie);

				reviewService.createNewReview(review);
			}

			else {
				review1.setRating(updateRating.getRatingValue());
				reviewService.updateReview(review1);

			}
			rating = movieService.getTotalRatingByMovieId(updateRating.getMovieId());
			return ResponseEntity.ok(rating);
		}

	}

	// deleteReview
	@GetMapping("review/delete/{id}")
	public String deleteReview(@PathVariable int id) {

		Review review = reviewService.getReviewById(id);
		reviewService.deleteReview(review);

		return "redirect:/movieDetails";
	}

}
