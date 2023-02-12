package com.movie.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.movie.app.model.MovieCategory;
import com.movie.app.model.User;
import com.movie.app.model.UserSession;
import com.movie.app.service.MovieCategoryService;
import com.movie.app.service.UserService;

@Controller
public class AccountController {

	@Autowired
	private UserService userService;

	@Autowired
	private MovieCategoryService movieCategoryService;

	@GetMapping("/createAccountForm")
	public String createAccountForm(Model model) {

		List<MovieCategory> movieCategories = movieCategoryService.findAllCategory();
		model.addAttribute("movieCategories", movieCategories);
		model.addAttribute("user", new User());
		return "account_create";
	}

	// createAccount
	@PostMapping("/createAccount")
	public String createNewAccount(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model,
			@RequestParam("categories")List<MovieCategory> categories) {
		if (bindingResult.hasErrors()) {
			List<MovieCategory> movieCategories = movieCategoryService.findAllCategory();
			model.addAttribute("movieCategories", movieCategories);
			return "account_create";
		}
		userService.createAccount(user, categories);
		return "redirect:/login";
	}

	// displayEditForm
	@GetMapping("/user/manageAccount")
	public String editAccountForm(Model model, HttpSession session) {

		List<MovieCategory> movieCategories = movieCategoryService.findAllCategory();
		model.addAttribute("movieCategories", movieCategories);

		UserSession usession = (UserSession) session.getAttribute("usession");
		int id = usession.getUser().getUserId();

		User user = userService.findUser(id);
		model.addAttribute("user", user);

		// change setPref String type to the List
		String s = user.getSetPref();
		String[] sArr = s.split("[|]");

		List<String> prefString = new ArrayList<>();
		for (String a : sArr) {
			prefString.add(a);
		}

		model.addAttribute("prefString", prefString);
		return "account_manage";
	}

	@PostMapping("/user/account/edit") 
	public String editAccount(
			@ModelAttribute @Valid User user, BindingResult result, Model model,
		@RequestParam("categories") List<MovieCategory> categories) {
		 if (result.hasErrors()) {
			List<MovieCategory> movieCategories = movieCategoryService.findAllCategory();
			model.addAttribute("movieCategories", movieCategories);
			List<String> prefString = new ArrayList<>();
			model.addAttribute("prefString",prefString);
		      return "account_manage";
		    } 
		userService.editAccount(user,categories);
		return "redirect:/login"; 
		}


	// deleteAccount
	@GetMapping("/user/account/delete/{id}")
	public String deleteAccount(@PathVariable int id) {

		User user = userService.findUser(id);
		userService.deleteAccount(user);

		return "forward:/login";
	}
}
