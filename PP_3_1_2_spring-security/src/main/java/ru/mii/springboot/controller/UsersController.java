package ru.mii.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mii.springboot.model.User;
import ru.mii.springboot.service.UserService;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class UsersController {

    private UserService userService;
    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("allusers", userService.getAllUsers());
        return "admin/allusers";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("userbyid", userService.getUserById(id));
        return "admin/userbyid";
    }

    @GetMapping("/saveuser")
    public String newUser(@ModelAttribute("newuser") User user) {
        return "admin/newuser";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("newuser") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/updateuser")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("edituser", userService.getUserById(id));
        return "/admin/edituser";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("edituser") User user, @PathVariable("id") int id) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
