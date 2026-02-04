package com.LMS.library.controller;

import com.LMS.library.model.Category;
import com.LMS.library.model.User;
import com.LMS.library.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    @Autowired
    private final UserService userService;

    @GetMapping
    public String getAllUsers(Model model){
        List<User> users = userService.getUsers();
        model.addAttribute("users" , users);
        return "user/user_list";
    }

    @GetMapping("/get/{id}")
    public String getUserById(@PathVariable Long id, Model model){
        User user = userService.getUserById(id);
        if(user==null)
            return "redirect:/user";

        model.addAttribute("user" , user);
        return "user/user_by_id";
    }

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/user_form";
    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute User user){
        List<Long> bookIds = List.of();

        try {
            if (user.getBookIdsJson() != null &&
                    !user.getBookIdsJson().isBlank()) {

                ObjectMapper mapper = new ObjectMapper();
                bookIds = mapper.readValue(
                        user.getBookIdsJson(),
                        new TypeReference<List<Long>>() {}
                );
            }

            userService.saveUserWithBooks(user, bookIds);

        } catch (Exception e) {
            System.out.println( e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/user";
    }

    @GetMapping("/put/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user/edit_user";
    }

    @PostMapping("/put/{id}")
    public String updateUser(@Valid @ModelAttribute User user , @PathVariable Long id){
        List<Long> bookIds = List.of();

        try {
            if (user.getBookIdsJson() != null &&
                    !user.getBookIdsJson().isBlank()) {

                ObjectMapper mapper = new ObjectMapper();
                bookIds = mapper.readValue(
                        user.getBookIdsJson(),
                        new TypeReference<List<Long>>() {
                        }
                );
            }

            userService.updateUserWithBooks(user, bookIds , id);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/user";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }
}