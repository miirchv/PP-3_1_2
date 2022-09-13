package ru.mii.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mii.springboot.model.User;
import ru.mii.springboot.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserService userService;
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{id}")
	public String getUserById(@PathVariable("id") int id, Model model) {
		model.addAttribute("userbyid", userService.getUserById(id));
		return "userpage";
	}


}