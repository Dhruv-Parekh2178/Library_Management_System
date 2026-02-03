package com.LMS.library.controller;

import com.LMS.library.model.Category;
import com.LMS.library.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequiredArgsConstructor
@RequestMapping("/library/category")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    @GetMapping("/getAll")
    public String getAllCategories(){
        List<Category> categories = categoryService.getCategories();
        return "";
    }

    @GetMapping("/get/{id}")
    public String getCategoryById(@PathVariable Long id){
        Category category = categoryService.getCategoryById(id);
        if(category==null)
            return "";
        return "";
    }

    @PostMapping("/add")
    public String addCategory(@RequestBody Category category){
        categoryService.saveCategory(category);
        return "";
    }

    @PostMapping("/put/{id}")
    public String updateCategory(@RequestBody Category category , @PathVariable Long id){
        Category savedCategory = categoryService.getCategoryById(id);
        if(savedCategory == null){
            return "";
        }
        categoryService.updateCategory(category , id);
        return "";
    }

    @PostMapping("/delete/{id}")
    public String DeleteAuthor(@PathVariable Long id){
        Category savedCategory = categoryService.getCategoryById(id);
        if(savedCategory == null){
            return  "";
        }
        categoryService.deleteCategory(id);
        return "";
    }
}
