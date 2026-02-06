package com.LMS.library.controller;


import com.LMS.library.model.MasterUser;
import com.LMS.library.security.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @GetMapping("/login")
    public String MasterUserLoginForm() {
        return "login";
    }

    @GetMapping("/signUp")
    public String MasterUserSignupForm(Model model){
        model.addAttribute("masteUser" , new MasterUser());
        return "signup";
    }

    @PostMapping("/signUp")
    public String MasterUserSignup(@Valid @ModelAttribute MasterUser masterUser){
        authService.signUp(masterUser);
        return "redirect:/index";
    }


}
