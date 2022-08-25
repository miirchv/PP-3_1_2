package ru.mii.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mii.springboot.model.User;
import ru.mii.springboot.service.UserService;


@Controller
public class HelloController {

	private UserService userService;
	@Autowired
	public HelloController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String start() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/registration")
	public String newUser(@ModelAttribute("newuser") User user) {
		return "registration";
	}

	@PostMapping("/registration")
	public String createUser(@ModelAttribute("newuser") User user) {
		userService.saveUser(user);
		return "redirect:/login";
	}

	@GetMapping("/{id}")
	public String getUserById(@PathVariable("id") int id, Model model) {
		model.addAttribute("userbyid", userService.getUserById(id));
		return "userpage";
	}


}