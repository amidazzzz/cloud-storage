package com.amida.cloudStorage.controller;

import com.amida.cloudStorage.model.User;
import com.amida.cloudStorage.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping
    public String processRegistration(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "redirect:/login";
    }
}
