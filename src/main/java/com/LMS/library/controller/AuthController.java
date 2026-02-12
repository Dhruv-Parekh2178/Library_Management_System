package com.LMS.library.controller;

import com.LMS.library.dtos.AuthRequest;
import com.LMS.library.model.MasterUser;
import com.LMS.library.security.AuthService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> doLogin(@RequestBody AuthRequest req, HttpServletResponse response) {

        System.out.println("Username: " + req.getName());
        System.out.println("Password: " + req.getPassword());

        String token = authService.doLogin(
                req.getName().trim(),
                req.getPassword().trim()
        );

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
        return "redirect:/login?logout";
    }

    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void checkCacheManager() {
        System.out.println("CACHE MANAGER = " + cacheManager.getClass());
    }

}
