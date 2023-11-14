package com.an.pp3.controller;

import com.an.pp3.model.User;
import com.an.pp3.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    private String sortedColumn = "id";
    private String sortedDirection = "ASC";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUsers(@RequestParam(required = false) String column,
                            @RequestParam(required = false) String direction,
                            ModelMap model) {
        if (column != null) {
            sortedColumn = column;
        }
        if (direction != null) {
            sortedDirection = direction;
        }
        List<User> users = userService.getAllUsersSorted(sortedColumn, sortedDirection);
        model.addAttribute("users", users);
        model.addAttribute("userSave", new User());
        return "users";
    }

    @PostMapping("add-user")
    public String addUser(@ModelAttribute("userSave") User userSave) {
        userService.saveUser(userSave);
        return "redirect:/users";
    }

    @DeleteMapping("remove-user/{id}")
    public String removeUser(@PathVariable long id) {
        userService.removeUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/show-update-user")
    public String showUpdateUser(@RequestParam long id, ModelMap model) {
        User userSave = userService.getUserById(id);
        model.addAttribute("userSave", userSave);
        return "update-user";
    }

    @PutMapping("/update-user")
    public String updateUser(@ModelAttribute("userSave") User userSave) {
        userService.saveUser(userSave);
        return "redirect:/users";
    }
}
