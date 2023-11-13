package com.an.pp3.controller;

import com.an.pp3.model.User;
import com.an.pp3.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    private String sortedColumn = "id";
    private String sortedDirection = "ASC";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Почему я использую проверку на null вместо Optional<String> column и orElse
    // С Optional в коде будет строка
    // sortedColumn = column.orElse(sortedColumn);
    // При column == null, в поле sortedColumn будет перезаписано это же поле.
    // А достаточно ничего не делать.
    // Присваивать переменной собственное значение это как-то странно.
    @GetMapping("/users")
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
        model.addAttribute("userAdd", new User());
        return "users";
    }

    @PostMapping("/add_user")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:users";
    }

    @GetMapping("/remove_user")
    public String removeUser(@RequestParam long id) {
        userService.removeUserById(id);
        return "redirect:users";
    }

    @GetMapping("/show_update_user")
    public String show_update_user(@RequestParam long id, ModelMap model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "show_update_user";
    }

    @PostMapping("/update_user")
    public String updateUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:users";
    }
}
