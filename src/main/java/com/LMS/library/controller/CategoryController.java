package com.LMS.library.controller;

import com.LMS.library.dtos.CategoryDTO;
import com.LMS.library.dtos.CategoryRequestDTO;
import com.LMS.library.model.ApiResponse;
import com.LMS.library.model.Category;
import com.LMS.library.service.book.BookService;
import com.LMS.library.service.category.CategoryService;
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
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final BookService bookService;

    @GetMapping
    public String getAllCategories(Model model){
        List<CategoryDTO> categories = categoryService.getCategories();
        model.addAttribute("categories" , categories);
        return "category/category_list";
    }

    @GetMapping("/data")
    @ResponseBody
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getAllCategoriesData(){
        List<CategoryDTO> categories = categoryService.getCategories();
        return ResponseEntity.ok(new ApiResponse<>(true , "success" , categories));
    }

    @GetMapping("/get/{id}")
    public String getCategoryById(@PathVariable Long id, Model model){
        CategoryDTO category = categoryService.getCategoryById(id);
        if(category==null)
            return "redirect:/category";

        model.addAttribute("category" , category);
        return "category/category_by_id";
    }

    @GetMapping("/get/{id}/data")
    public ResponseEntity<ApiResponse<CategoryDTO>> getCategoryByIdData(@PathVariable Long id){
        CategoryDTO category = categoryService.getCategoryById(id);
        if(category==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false ,"fail" , null));

        return ResponseEntity.ok(new ApiResponse<>(true, "success" , category));
    }


    @GetMapping("/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new CategoryRequestDTO());
        model.addAttribute("books", bookService.getbooks());
        return "category/category_form";
    }


    @GetMapping("/add/data")
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> addCategoryData(Model model) {
        return ResponseEntity.ok(new ApiResponse<>(true,"success","Category added successfully."));
    }

    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute CategoryRequestDTO categoryRequestDTO){


            categoryService.saveCategoryWithBooks(categoryRequestDTO, categoryRequestDTO.getBookIds());

        return "redirect:/category";
    }

    @GetMapping("/put/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        CategoryDTO category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        model.addAttribute("books", bookService.getbooks());
        return "category/edit_category";
    }

    @GetMapping("/put/{id}/data")
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> updateCategoryData(@PathVariable Long id) {
        CategoryDTO savedCategory = categoryService.getCategoryById(id);
        if(savedCategory == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "fail","Category with id " + id + " is not found." ));
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "success" ,"Category with id " + id + " updated successfully."));
    }

    @PostMapping("/put/{id}")
    public String updateCategory(@Valid @ModelAttribute CategoryRequestDTO categoryRequestDTO , @PathVariable Long id){
            categoryService.updateCaregoryWithBooks(categoryRequestDTO, categoryRequestDTO.getBookIds() , id);
        return "redirect:/category";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/category";
    }

    @GetMapping("/delete/{id}/data")
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> DeleteAuthor(@PathVariable Long id){
        CategoryDTO savedCategory = categoryService.getCategoryById(id);
        if(savedCategory == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "fail","Category with id " + id + " is not found." ));
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "success" ,"Category with id " + id + " deleted successfully."));
    }
}
