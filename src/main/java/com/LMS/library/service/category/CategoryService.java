package com.LMS.library.service.category;

import com.LMS.library.dtos.CategoryDTO;
import com.LMS.library.dtos.CategoryRequestDTO;
import com.LMS.library.model.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getCategories();

    CategoryDTO getCategoryById(Long id);

    void deleteCategory(Long id);

    void saveCategoryWithBooks(CategoryRequestDTO categoryRequestDTO, List<Long> bookIds);

    CategoryDTO updateCaregoryWithBooks(CategoryRequestDTO categoryRequestDTO, List<Long> bookIds, Long id);
}