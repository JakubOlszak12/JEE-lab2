package com.example.demo.controllers;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao dao;
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/register")
    public String registerPage(Model m) {
        m.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String registerPagePOST(@Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
        return "redirect:/login";
    }
    @GetMapping("/profile")
    public String profilePage(Model m, Principal principal) {
        m.addAttribute("user", dao.findByLogin(principal.getName()));
        return "profile";
    }
    @GetMapping("/users")
    public String getUsers(Model model) {
        Iterable<User> users = dao.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(("/delete"))
    public String deleteUser(Model model, Principal principal) {
        User user = dao.findByLogin(principal.getName());
        dao.delete(user);
        return "redirect:/logout";
    }

    @GetMapping("/edit")
    public String editProfile(Model m, Principal principal) {
        String username = principal.getName();
        User user = dao.findByLogin(username);
        m.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/edit")
    public String editUserProfile(@Valid User user, BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()){
            return "edit";
        }
        String username = principal.getName();
        User userInDatabase = dao.findByLogin(username);
        userInDatabase.setPassword(passwordEncoder.encode(user.getPassword()));
        userInDatabase.setSurname(user.getSurname());
        userInDatabase.setName(user.getName());
        userInDatabase.setLogin(user.getLogin());
        dao.save(userInDatabase);

        return "redirect:/profile";
    }
}

