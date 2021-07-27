package ru.kolyadina.spring_boot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kolyadina.spring_boot.model.Role;
import ru.kolyadina.spring_boot.model.User;
import ru.kolyadina.spring_boot.service.RoleService;
import ru.kolyadina.spring_boot.service.UserService;

import java.util.HashSet;
import java.util.Set;


@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/")
    public String login(){
        return "login";
    }

    @GetMapping(value = "/user")
    public String getUserInfo(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        return "userPanel";
    }

    @GetMapping(value = "/admin")
    public String getAllUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles",user.getRoles());
        model.addAttribute("allUsers", userService.getAllUsers());
        return "adminPanel";
    }

    @GetMapping(value = "/admin/new")
    public String newUser(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("userAdmin",user);
        model.addAttribute("roles", roleService.getAllRole());
        return "newUser";
    }

    @PostMapping(value = "/admin/add")
    private String addUser(@ModelAttribute User user, @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {
        Set<Role> roles = new HashSet<>();
        for (String role : checkBoxRoles) {
            roles.add(roleService.getRoleByName(role));
        }
        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PutMapping(value = "/admin/edit")
    public String updateUser(@ModelAttribute User user, @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {
        Set<Role> roles = new HashSet<>();
        for (String role : checkBoxRoles) {
            roles.add(roleService.getRoleByName(role));
        }
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/remove/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
