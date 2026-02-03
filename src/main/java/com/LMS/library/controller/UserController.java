package com.LMS.library.controller;

import com.LMS.library.model.User;
import com.LMS.library.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@Controller
@RequestMapping("/library/user")
@RequiredArgsConstructor
public class UserController {


    @Autowired
    private final UserService userService;

    @GetMapping("/getAll")
    public String getAllUsers(){
        List<User> users = userService.getUsers();
        return "";
    }

    @GetMapping("/get/{id}")
    public String getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        if(user==null)
            return "" ;
        return "";
    }

    @PostMapping("/add")
    public String addAuthor(@RequestBody User user){
        userService.saveUser(user);
        return "" ;
    }

    @PostMapping("/put/{id}")
    public String updateAuthor(@RequestBody User user , @PathVariable Long id){
        User savedUser = userService.getUserById(id);
        if(savedUser == null){
            return "" ;
        }
        userService.updateUser(user , id);
        return "";
    }

    @PostMapping("/delete/{id}")
    public String DeleteAuthor(@PathVariable Long id){
        User savedUser = userService.getUserById(id);
        if(savedUser == null){
            return "";
        }
        userService.deleteUser(id);
        return "";
    }
}