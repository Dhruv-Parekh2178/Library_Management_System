package com.LMS.library.controller;

import com.LMS.library.model.ApiResponse;
import com.LMS.library.model.Author;
import com.LMS.library.model.User;
import com.LMS.library.service.author.AuthorService;
import com.LMS.library.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@Controller
@RequestMapping("/library")
@RequiredArgsConstructor
public class UserController {


    @Autowired
    private final UserService userService;

    @GetMapping("/user/getAll")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers(){
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(new ApiResponse<>(true , "success" , users));
    }

    @GetMapping("/user/get/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        if(user==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false ,"fail" , null));
        return ResponseEntity.ok(new ApiResponse<>(true, "success" , user));
    }

    @PostMapping("/user/add")
    public ResponseEntity<ApiResponse<String>> addAuthor(@RequestBody User user){
        userService.saveUser(user);
        return ResponseEntity.ok(new ApiResponse<>(true,"success","User added successfully."));
    }

    @PostMapping("/user/put/{id}")
    public ResponseEntity<ApiResponse<String>> updateAuthor(@RequestBody User user , @PathVariable Long id){
        User savedUser = userService.getUserById(id);
        if(savedUser == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "fail","User with id " + id + " is not found." ));
        }
        userService.updateUser(user , id);
        return ResponseEntity.ok(new ApiResponse<>(true, "success" ,"User with id " + id + " updated successfully."));
    }

    @PostMapping("/user/delete/{id}")
    public ResponseEntity<ApiResponse<String>> DeleteAuthor(@PathVariable Long id){
        User savedUser = userService.getUserById(id);
        if(savedUser == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "fail","User with id " + id + " is not found." ));
        }
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "success" ,"user with id " + id + " deleted successfully."));
    }
}
