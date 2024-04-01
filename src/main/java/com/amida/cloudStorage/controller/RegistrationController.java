package com.amida.cloudStorage.controller;

import com.amida.cloudStorage.dto.UserDto;
import com.amida.cloudStorage.mapper.UserDtoMapper;
import com.amida.cloudStorage.model.User;
import com.amida.cloudStorage.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserDtoMapper userDtoMapper;

    public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder, UserDtoMapper userDtoMapper) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping
    public String processRegistration(@ModelAttribute @Valid UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userDtoMapper.apply(userDto);
        userRepo.save(user);
        return "redirect:/login";
    }
}
