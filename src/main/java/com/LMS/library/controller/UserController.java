package com.LMS.library.controller;

import com.LMS.library.dtos.UserDTO;
import com.LMS.library.dtos.UserRequestDTO;
import com.LMS.library.model.ApiResponse;
import com.LMS.library.model.User;
import com.LMS.library.service.book.BookService;
import com.LMS.library.service.user.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BookService bookService;

    @GetMapping
    public String getAllUsers(Model model){
        List<UserDTO> users = userService.getUsers();
        model.addAttribute("users" , users);
        return "user/user_list";
    }

    @GetMapping("/data")
    @ResponseBody
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsersData(){
        List<UserDTO> users = userService.getUsers();
        return ResponseEntity.ok(new ApiResponse<>(true , "success" , users));
    }

    @GetMapping("/get/{id}")
    public String getUserById(@PathVariable Long id, Model model){
        UserDTO user = userService.getUserById(id);
        if(user==null)
            return "redirect:/user";

        model.addAttribute("user" , user);
        return "user/user_by_id";
    }

    @GetMapping("/get/{id}/data")
    public ResponseEntity<ApiResponse<UserDTO>> getUserByIdData(@PathVariable Long id){
        UserDTO user = userService.getUserById(id);
        if(user==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false ,"fail" , null));


        return ResponseEntity.ok(new ApiResponse<>(true, "success" , user));
    }

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new UserRequestDTO());
        model.addAttribute("books", bookService.getbooks());
        return "user/user_form";
    }

    @GetMapping("/add/data")
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> addUserData() {
        return ResponseEntity.ok(new ApiResponse<>(true,"success","User added successfully."));
    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute UserRequestDTO userRequestDTO){
            userService.saveUserWithBooks(userRequestDTO, userRequestDTO.getBookIds());

        return "redirect:/user";
    }

    @GetMapping("/put/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        UserDTO savedUser = userService.getUserById(id);
        model.addAttribute("user", savedUser);
        model.addAttribute("books", bookService.getbooks());
        return "user/edit_user";
    }

    @GetMapping("/put/{id}/data")
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> updateUserData(@PathVariable Long id) {
        UserDTO savedUser = userService.getUserById(id);
        if(savedUser == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "fail","User with id " + id + " is not found." ));
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "success" ,"User with id " + id + " updated successfully."));
    }

    @PostMapping("/put/{id}")
    public String updateUser(@Valid @ModelAttribute UserRequestDTO userRequestDTO , @PathVariable Long id){
            userService.updateUserWithBooks(userRequestDTO, userRequestDTO.getBookIds() , id);
        return "redirect:/user";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }

    @GetMapping("/delete/{id}/data")
    public ResponseEntity<ApiResponse<String>> deleteUserData(@PathVariable Long id) {
        UserDTO savedUser = userService.getUserById(id);
        if(savedUser == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "fail","User with id " + id + " is not found." ));
        }
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "success" ,"user with id " + id + " deleted successfully."));
    }
}