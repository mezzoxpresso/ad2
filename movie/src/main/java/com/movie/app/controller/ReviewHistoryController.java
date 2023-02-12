package com.movie.app.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.movie.app.model.Review;
import com.movie.app.model.UserSession;
import com.movie.app.service.ReviewService;



@Controller
@RequestMapping("/user")
public class ReviewHistoryController {
	
	@Autowired
	private ReviewService reviewService;
	

	@GetMapping("/reviewHistory")
	public String reviewHistory(Model model,HttpSession session) {

		UserSession usession = (UserSession) session.getAttribute("usession");
		int uid = usession.getUser().getUserId();
		
		List<Review> reviewList = reviewService.findAllById(uid);
		model.addAttribute("reviewList", reviewList);
		return "review_history";
	}
	
	@GetMapping("/reviewHistory/delete/{id}")
	public String deleteReview(@PathVariable int id) {
		Review review = reviewService.getReviewById(id);
		reviewService.deleteReview(review);
		return "redirect:/user/reviewHistory";
	}
}
