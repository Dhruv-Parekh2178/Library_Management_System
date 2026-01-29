package com.LMS.library.controller;

import com.LMS.library.model.ApiResponse;
import com.LMS.library.model.Author;
import com.LMS.library.model.Category;
import com.LMS.library.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/library")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    @GetMapping("/category/getAll")
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategories(){
        List<Category> categories = categoryService.getCategories();
        return ResponseEntity.ok(new ApiResponse<>(true , "success" , categories));
    }

    @GetMapping("/category/get/{id}")
    public ResponseEntity<ApiResponse<Category>> getCategoryById(@PathVariable Long id){
        Category category = categoryService.getCategoryById(id);
        if(category==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false ,"fail" , null));
        return ResponseEntity.ok(new ApiResponse<>(true, "success" , category));
    }

    @PostMapping("/category/add")
    public ResponseEntity<ApiResponse<String>> addCategory(@RequestBody Category category){
        categoryService.saveCategory(category);
        return ResponseEntity.ok(new ApiResponse<>(true,"success","Author added successfully."));
    }

    @PostMapping("/category/put/{id}")
    public ResponseEntity<ApiResponse<String>> updateCategory(@RequestBody Category category , @PathVariable Long id){
        Category savedCategory = categoryService.getCategoryById(id);
        if(savedCategory == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "fail","Author with id " + id + " is not found." ));
        }
        categoryService.updateCategory(category , id);
        return ResponseEntity.ok(new ApiResponse<>(true, "success" ,"Author with id " + id + " updated successfully."));
    }

    @PostMapping("/category/delete/{id}")
    public ResponseEntity<ApiResponse<String>> DeleteAuthor(@PathVariable Long id){
        Category savedCategory = categoryService.getCategoryById(id);
        if(savedCategory == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "fail","Author with id " + id + " is not found." ));
        }
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "success" ,"Author with id " + id + " deleted successfully."));
    }
}

