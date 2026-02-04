package com.LMS.library.controller;

import com.LMS.library.model.Author;
import com.LMS.library.model.Category;
import com.LMS.library.service.category.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.List;
@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    @GetMapping
    public String getAllCategories(Model model){
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories" , categories);
        return "category/category_list";
    }

    @GetMapping("/get/{id}")
    public String getCategoryById(@PathVariable Long id, Model model){
        Category category = categoryService.getCategoryById(id);
        if(category==null)
            return "redirect:/category";

        model.addAttribute("category" , category);
        return "category/category_by_id";
    }

    @GetMapping("/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/category_form";
    }

    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute Category category){
        List<Long> bookIds = List.of();

        try {
            if (category.getBookIdsJson() != null &&
                    !category.getBookIdsJson().isBlank()) {

                ObjectMapper mapper = new ObjectMapper();
                bookIds = mapper.readValue(
                        category.getBookIdsJson(),
                        new TypeReference<List<Long>>() {}
                );
            }

            categoryService.saveCategoryWithBooks(category, bookIds);

        } catch (Exception e) {
            System.out.println( e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/category";
    }

    @GetMapping("/put/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "category/edit_category";
    }

    @PostMapping("/put/{id}")
    public String updateCategory(@Valid @ModelAttribute Category category , @PathVariable Long id){
        List<Long> bookIds = List.of();

        try {
            if (category.getBookIdsJson() != null &&
                    !category.getBookIdsJson().isBlank()) {

                ObjectMapper mapper = new ObjectMapper();
                bookIds = mapper.readValue(
                        category.getBookIdsJson(),
                        new TypeReference<List<Long>>() {
                        }
                );
            }

            categoryService.updateCaregoryWithBooks(category, bookIds , id);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/category";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/category";
    }
}
