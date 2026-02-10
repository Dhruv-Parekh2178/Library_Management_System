package com.LMS.library.controller;

import com.LMS.library.dtos.AuthRequest;
import com.LMS.library.model.MasterUser;
import com.LMS.library.security.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/doLogin")
    @ResponseBody
    public ResponseEntity<String> doLogin(@RequestBody AuthRequest req, HttpServletResponse response) {

        System.out.println("Username: " + req.getName());
        System.out.println("Password: " + req.getPassword());

        String token = authService.doLogin(
                req.getName().trim(),
                req.getPassword().trim()
        );

        Cookie cookie = new Cookie("JWT", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);
        System.out.println("token " + token);
        return ResponseEntity.ok(token);
    }


    @GetMapping("/signUp")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signUp")
    public String signup(MasterUser user) {
        authService.signUp(user);
        return "redirect:/login";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("JWT", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/login?logout";
    }

}
