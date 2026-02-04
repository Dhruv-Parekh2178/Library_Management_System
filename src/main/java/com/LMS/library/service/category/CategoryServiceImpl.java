package com.LMS.library.service.category;

import com.LMS.library.exception.ResourceNotFoundException;
import com.LMS.library.model.Author;
import com.LMS.library.model.Book;
import com.LMS.library.model.Category;
import com.LMS.library.repository.BookRepository;
import com.LMS.library.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class  CategoryServiceImpl implements CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final BookRepository bookRepository;

    @Override
    public List<Category> getCategories() {
        List<Category> categories = categoryRepository.findAll().stream()
                .filter(category -> !category.isDeleted()).toList();
        return categories;
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findCategoryById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category" ,"CategoryId" , id));
    }

    @Override
    public void deleteCategory(Long id) {
        Category savedCategory = categoryRepository.findCategoryById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category" , "CateegoryId" , id));

        savedCategory.setDeleted(true);
        categoryRepository.save(savedCategory);
    }

    @Override
    public void saveCategoryWithBooks(Category category, List<Long> bookIds) {
        List<Book> books = bookRepository.findAllById(bookIds);
        if (books.size() != bookIds.size()) {
            throw new RuntimeException("One or more Book IDs are invalid");
        }

        category.setBooks(books);

        categoryRepository.save(category);
    }

    @Override
    public void updateCaregoryWithBooks(Category category, List<Long> bookIds, Long id) {
        Category savedCategory = categoryRepository.findCategoryById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "CatgoryId", id));


        savedCategory.setName(category.getName());

        savedCategory.setDeleted(category.isDeleted());
        List<Book> books = bookRepository.findAllById(bookIds);
        if (books.size() != bookIds.size()) {
            throw new RuntimeException("One or more Book IDs are invalid");
        }

        savedCategory.setBooks(books);

        categoryRepository.save(savedCategory);
    }
}