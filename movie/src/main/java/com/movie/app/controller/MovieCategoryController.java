package com.movie.app.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.movie.app.model.MovieCategory;
import com.movie.app.service.MovieCategoryService;

@Controller
@RequestMapping("/admin")
public class MovieCategoryController {
	
	@Autowired
	private MovieCategoryService movieCategoryService;
	
	//formDisplay
	@GetMapping("/categoryForm")
	public String createCategoryForm(Model model) {
		List<MovieCategory> movieCategory = movieCategoryService.findAllCategory();
		
		model.addAttribute("movieCategory",movieCategory);
		model.addAttribute("category",new MovieCategory());
		return "manage_category";
	}
	
	//create
	@PostMapping("/createCategory")
	public String createCategory(@ModelAttribute MovieCategory category) {
		movieCategoryService.createCategory(category);
		return "redirect:/admin/categoryForm";
	}
	
	//delete
	@GetMapping("/category/delete/{id}")
	public String deleteCategory(@PathVariable int id) {
		MovieCategory category = movieCategoryService.findCategoryById(id);
		movieCategoryService.deleteCategory(category);
		return "redirect:/admin/categoryForm";
	}
	
	//editFormDisplay
	@GetMapping("/category/edit/{id}")
	public String editCategoryForm(@PathVariable int id,Model model) {
		System.out.println(id);
		MovieCategory movieCategory = movieCategoryService.findCategoryById(id);
		model.addAttribute("movieCategory",movieCategory);
		return "category_edit";
	}
	
	@PostMapping("/category/edit")
	public String editCategory(@ModelAttribute MovieCategory category) {
		movieCategoryService.editCategory(category);
		
		return "redirect:/admin/categoryForm";
	}
	
}
