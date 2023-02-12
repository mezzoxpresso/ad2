package com.movie.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.movie.app.model.User;
import com.movie.app.service.UserService;

@Controller

public class UserController {

 @Autowired
 private UserService userService;
 

 @GetMapping("/admin/userList")
 public String getAllCreatedUserList(Model model) {
  
  List<User> userList = userService.findAllUsers();
  model.addAttribute("userList",userList);
  
  return "userlist";
 }
 
 // deleteUser
 @GetMapping("/admin/user/delete/{id}")
 public String deleteUser(@PathVariable int id) {

  User user = userService.findUser(id);
  userService.deleteAccount(user);

  return "redirect:/admin/userList";
 }
}