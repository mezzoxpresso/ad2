package com.movie.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.movie.app.service.MovieService;
import com.movie.app.service.UserService;
import com.movie.app.model.UserSession;
import com.movie.app.model.User;
import com.movie.app.repository.MovieRepository;


@Controller
public class CommonController {
  @Autowired
  private UserService userService;
  
  @Autowired
  MovieService movieService;

  @Autowired
  MovieRepository movieRepo;

  
  @RequestMapping(value = {"/", "/login", "/home"}, method = RequestMethod.GET)
  public String login(Model model) {
    model.addAttribute("user", new User());
    
    return "login";
  }
  
  @RequestMapping(value = "/home/authenticate")
  public String authenticate(@ModelAttribute User user, BindingResult bindingResult, Model model,
		  HttpSession session) {
    if (bindingResult.hasErrors()) {
      return "login";
    } 
    
    User u = userService.authenticate(user.getUserName(), user.getPassword());
    if (u == null) {
      model.addAttribute("loginMessage", "Incorrect username/password");
      return "login";
    }
    
    // Login ok, let's put the user info into a session
    // a. The user object itself
    UserSession userSession = new UserSession();
    userSession.setUser(u);
    
    session.setAttribute("usession", userSession);
    
    List<String> roleNames = u.getRoleNames();
    System.out.println("Roles:");
    roleNames.forEach(System.out::println);
    
    // Done, let's redirect to respective page
    if (roleNames.contains("Admin")) {
      return "redirect:/admin/userList";
    }
    
    if (roleNames.contains("User")) {
      return "redirect:/user/reviewHistory";
    }
    
    return "redirect:/movieDetails";
  }
  
  @GetMapping("/about")
  public String home() {
    return "about";
  }
  
  @RequestMapping(value = "/logout")
  public String logout(HttpSession session) {
	 session.invalidate();
    return "redirect:/login";
  }
}
