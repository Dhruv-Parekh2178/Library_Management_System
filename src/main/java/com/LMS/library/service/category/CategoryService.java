package com.LMS.library.service.category;

import com.LMS.library.model.Author;
import com.LMS.library.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();

    Category getCategoryById(Long id);

//    void saveCategory(Category category);
//
//    void updateCategory(Category category, Long id);

    void deleteCategory(Long id);

    void saveCategoryWithBooks(Category category, List<Long> bookIds);

    void updateCaregoryWithBooks(Category category, List<Long> bookIds, Long id);
}